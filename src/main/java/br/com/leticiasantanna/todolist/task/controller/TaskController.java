package br.com.leticiasantanna.todolist.task.controller;

import br.com.leticiasantanna.todolist.task.repository.TaskRepository;
import br.com.leticiasantanna.todolist.task.entity.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/create")
 public TaskModel createTask(@RequestBody TaskModel taskModel) {

        return taskRepository.save(taskModel);
    }
}
