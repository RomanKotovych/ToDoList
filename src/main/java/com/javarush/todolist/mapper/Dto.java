package com.javarush.todolist.mapper;

import com.javarush.todolist.model.dto.TaskTo;
import com.javarush.todolist.model.entity.Task;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {Dto.class})
public interface Dto {
    Dto MAPPER = Mappers.getMapper(Dto.class);

    Task toEntity(TaskTo taskDto);

    TaskTo toDto(Task task);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Task partialUpdate(TaskTo taskDto, @MappingTarget Task task);
}
