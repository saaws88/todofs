package com.nekot.todoBack.dto;

import com.nekot.todoBack.model.ToDo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ToDoDto {
  private Long id;
  private String name;
  private Boolean completed;

  public ToDoDto(ToDo entity) {
    this.id = entity.getId();
    this.name = entity.getName();
    this.completed = entity.getCompleted();
  }
}
