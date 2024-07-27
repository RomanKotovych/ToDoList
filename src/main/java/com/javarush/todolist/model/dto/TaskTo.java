package com.javarush.todolist.model.dto;

import com.javarush.todolist.model.entity.Status;
import lombok.Value;

import java.io.Serializable;


@Value
public class TaskTo implements Serializable {
    Integer id;
    String description;
    Status status;
}