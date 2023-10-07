package com.nekot.todoBack.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nekot.todoBack.model.ToDo;

public interface ToDoRepo extends JpaRepository<ToDo, Long> {
  List<ToDo> findByCompleted(Boolean completed);
}
