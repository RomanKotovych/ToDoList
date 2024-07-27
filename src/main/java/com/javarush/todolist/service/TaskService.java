package com.javarush.todolist.service;

import com.javarush.todolist.mapper.Dto;
import com.javarush.todolist.model.dto.TaskTo;
import com.javarush.todolist.model.entity.Task;
import com.javarush.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<TaskTo> getAll(Pageable pageable){
        Page<Task> taskPage = taskRepository.findAll(pageable);

        return taskPage.stream()
                .map(Dto.MAPPER::toDto)
                .toList();
    }

    public List<TaskTo> getAll(){
        return taskRepository.findAll()
                .stream()
                .map(Dto.MAPPER::toDto)
                .toList();
    }


    public TaskTo get(Long id){
        return Dto.MAPPER.toDto(taskRepository.findById(id).orElse(null));
    }

    public TaskTo create(TaskTo taskTo){
        return Dto.MAPPER.toDto(taskRepository.save(Dto.MAPPER.toEntity(taskTo)));
    }

    public TaskTo update(TaskTo taskTo){
        return Dto.MAPPER.toDto(taskRepository.save(Dto.MAPPER.toEntity(taskTo)));
    }

    public void delete(Long id){
        taskRepository.deleteById(id);
    }

    public Page<Task> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return taskRepository.findAll(pageable);
    }
}
