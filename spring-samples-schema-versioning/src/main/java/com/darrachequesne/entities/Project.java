package com.darrachequesne.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project")
@NoArgsConstructor
@RequiredArgsConstructor
public class Project extends AbstractEntity {

  /**
   * @since schema v1
   */
  @Getter
  @Setter
  @Column(nullable = false, length = 45)
  @NonNull
  private String name;

  /**
   * @since schema v1
   */
  @Getter
  @Setter
  @OneToMany(mappedBy = "project")
  private Set<Task> tasks;

  @Override
  public String toString() {
    return "Project [id=" + id + ", name=" + name + "]";
  }

}
