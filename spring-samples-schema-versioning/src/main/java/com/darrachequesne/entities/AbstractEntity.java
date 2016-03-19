package com.darrachequesne.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

  /**
   * @since schema v1
   */
  @Id
  @Column(columnDefinition = "char(36)") // or BINARY(16)
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  protected String id;

}
