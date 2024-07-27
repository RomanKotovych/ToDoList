package com.javarush.todolist.controller.view;

import com.javarush.todolist.model.dto.TaskTo;
import com.javarush.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/", "index", "/tasks"})
public class IndexController {
    private final TaskService taskService;

    @GetMapping
    public String index(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, Model model) {
        Pageable pageable = PageRequest.of(page, size);
        List<TaskTo> tasks = taskService.getAll(pageable);
        model.addAttribute("tasks", tasks);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", taskService.findPaginated(page + 1, size).getTotalPages());
        return "index";
    }
}

