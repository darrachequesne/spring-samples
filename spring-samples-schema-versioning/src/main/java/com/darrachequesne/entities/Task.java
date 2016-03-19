package com.darrachequesne.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "task")
@NoArgsConstructor
@RequiredArgsConstructor
public class Task extends AbstractEntity {

  @Getter
  @Setter
  @Column(nullable = false, length = 45)
  @NonNull
  private String name;

  @Getter
  @Setter
  @Column(length = 45)
  private String description;

  @Getter
  @Setter
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Project.class)
  private Project project;

  @Override
  public String toString() {
    return "Task [id=" + id + ", description=" + description + ", name=" + name + "]";
  }

}
