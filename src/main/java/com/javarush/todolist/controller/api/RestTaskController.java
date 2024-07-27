package com.javarush.todolist.controller.api;

import com.javarush.todolist.model.dto.TaskTo;
import com.javarush.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/tasks")
public class RestTaskController {
    private final TaskService taskService;

    @GetMapping
    public List<TaskTo> getAll(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public TaskTo getById(@PathVariable Long id) {
        return taskService.get(id);
    }

    @PostMapping
    public ResponseEntity<TaskTo> create(@RequestBody TaskTo taskTo) {
        return new ResponseEntity<>(taskService.create(taskTo), HttpStatus.CREATED);
    }

    @PutMapping
    public TaskTo update(@RequestBody TaskTo taskTo) {
        return taskService.update(taskTo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskTo> delete(@PathVariable Long id) {
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
