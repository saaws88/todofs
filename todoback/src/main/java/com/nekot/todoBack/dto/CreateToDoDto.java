package com.nekot.todoBack.dto;

import lombok.Data;

@Data
public class CreateToDoDto {
  private String name;
  private boolean completed;
}
