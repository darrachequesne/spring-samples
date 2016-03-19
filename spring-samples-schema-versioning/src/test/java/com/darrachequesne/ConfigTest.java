package com.darrachequesne;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.mariadb.jdbc.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.ScriptException;

@Configuration
@ComponentScan
public class ConfigTest {

  @Bean
  public DataSource dataSource() throws ScriptException, SQLException {
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
    dataSource.setDriver(new Driver());
    dataSource.setUrl("jdbc:mariadb://localhost:3306/sample");
    dataSource.setUsername("user");
    dataSource.setPassword("user");
    return dataSource;
  }

}