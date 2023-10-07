package com.nekot.todoBack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nekot.todoBack.dto.CreateToDoDto;
import com.nekot.todoBack.dto.ToDoDto;
import com.nekot.todoBack.dto.UpdateToDoDto;
import com.nekot.todoBack.model.ToDo;
import com.nekot.todoBack.repo.ToDoRepo;


@Service
public class ToDoService {
  
  private ToDoRepo toDoRepo;

  public ToDoService(ToDoRepo toDoRepo) {
    this.toDoRepo = toDoRepo;
  }

  public ToDoDto createToDo(CreateToDoDto createToDoDto) {
    ToDo newToDo = new ToDo();
    newToDo.setName(createToDoDto.getName());
    newToDo.setCompleted(createToDoDto.isCompleted());
    ToDo toDo = toDoRepo.save(newToDo);
    return new ToDoDto(toDo);   
  }

  public List<ToDoDto> getToDos() {
    List<ToDo> toDos = toDoRepo.findAll();
    return toDos.stream().map(entity -> new ToDoDto(entity)).toList();
  }

  public List<ToDoDto> getToDos(Boolean completed) {
    List<ToDo> toDos = toDoRepo.findByCompleted(completed);
    return toDos.stream().map(entity -> new ToDoDto(entity)).toList();
  }

  public ToDoDto getToDoById(Long id) {
    Optional<ToDo> toDo = toDoRepo.findById(id);
    if (toDo.isPresent()) {
      return new ToDoDto(toDo.get());
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
    }
  }

  public ToDoDto updateToDoDto(Long id, UpdateToDoDto updateToDoDto) {
    Optional<ToDo> toDo = toDoRepo.findById(id);
    if (toDo.isPresent()) {
      toDo.get().setName(updateToDoDto.getName());
      toDo.get().setCompleted(updateToDoDto.getCompleted());
      toDoRepo.save(toDo.get());
      return new ToDoDto(toDo.get());
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
    }
  }

  public void deleteToDo(Long id) {
    Optional<ToDo> toDo = toDoRepo.findById(id);
    if (toDo.isPresent()) {
      toDoRepo.delete(toDo.get());
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
    }
  }

}
