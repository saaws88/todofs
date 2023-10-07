package com.nekot.todoBack.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nekot.todoBack.dto.CreateToDoDto;
import com.nekot.todoBack.dto.ToDoDto;
import com.nekot.todoBack.dto.UpdateToDoDto;
import com.nekot.todoBack.service.ToDoService;

@RestController
@RequestMapping("/todos")
public class ToDoController {
  private ToDoService toDoService;

  public ToDoController (ToDoService toDoService) {
    this.toDoService = toDoService;
  }

  @PostMapping("")
  public ResponseEntity<ToDoDto> createToDo(@RequestBody CreateToDoDto newToDo) {
    ToDoDto toDoDto = toDoService.createToDo(newToDo);
    return new ResponseEntity<ToDoDto>(toDoDto, HttpStatus.CREATED);
  }

  @GetMapping("")
  public List<ToDoDto> getToDos(@RequestParam Optional<Boolean> completed) {
    if (completed.isPresent()) {
      return toDoService.getToDos(completed.get());
    }
    return toDoService.getToDos();
  }

  @GetMapping("/{id}")
  public ToDoDto getToDoById(@PathVariable Long id) {
    return toDoService.getToDoById(id);
  }

  @PutMapping("/{id}")
  public ToDoDto updateToDo(@PathVariable Long id, @RequestBody UpdateToDoDto updateToDo) {
    return toDoService.updateToDoDto(id, updateToDo);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteToDo(@PathVariable Long id) {
    toDoService.deleteToDo(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}



