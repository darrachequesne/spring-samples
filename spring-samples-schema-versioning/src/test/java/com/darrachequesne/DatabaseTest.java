package com.darrachequesne;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.hibernate.engine.jdbc.internal.Formatter;
import org.hibernate.tool.hbm2ddl.DatabaseMetadata;
import org.hibernate.tool.hbm2ddl.SchemaUpdateScript;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ConfigTest.class })
public class DatabaseTest {

  private static final String version = "2";

  private static final String previousVersion = "1";

  @Autowired
  private DataSource dataSource;

  @Test
  public void currentSchemaVersionDoesMatchMappings() throws SQLException {
    String currentSchema = String.format("sql/schema_v%s.sql", version);
    Resource currentSchemaResource = new ClassPathResource(currentSchema);
    ScriptUtils.executeSqlScript(dataSource.getConnection(), currentSchemaResource);

    LocalSessionFactoryBuilder sessionFactory = new LocalSessionFactoryBuilder(dataSource);
    sessionFactory.scanPackages(this.getClass().getPackage().getName());
    Dialect dialect = new MySQL5Dialect();
    DatabaseMetadata metadata = new DatabaseMetadata(dataSource.getConnection(), dialect, sessionFactory);
    List<SchemaUpdateScript> scripts = sessionFactory.generateSchemaUpdateScriptList(dialect, metadata);

    Formatter formatter = FormatStyle.DDL.getFormatter();
    for (SchemaUpdateScript script : scripts) {
      System.err.println(formatter.format(script.getScript()) + ";");
    }
    assertTrue(scripts.isEmpty());
  }

  @Test
  public void schemaMigrationIsValid() throws SQLException {
    if (previousVersion == null)
      return;

    String previousSchema = String.format("sql/schema_v%s.sql", previousVersion);
    String migrationScript = String.format("sql/schema_v%s_to_v%s.sql", previousVersion, version);
    Resource previousSchemaResource = new ClassPathResource(previousSchema);
    Resource migrationScriptResource = new ClassPathResource(migrationScript);
    ScriptUtils.executeSqlScript(dataSource.getConnection(), previousSchemaResource);
    ScriptUtils.executeSqlScript(dataSource.getConnection(), migrationScriptResource);

    LocalSessionFactoryBuilder sessionFactory = new LocalSessionFactoryBuilder(dataSource);
    sessionFactory.scanPackages(this.getClass().getPackage().getName());
    Dialect dialect = new MySQL5Dialect();
    DatabaseMetadata metadata = new DatabaseMetadata(dataSource.getConnection(), dialect, sessionFactory);
    sessionFactory.validateSchema(dialect, metadata);
  }

}
