package br.com.leticiasantanna.todolist.task.controller;

import br.com.leticiasantanna.todolist.task.entity.TaskModel;
import br.com.leticiasantanna.todolist.task.repository.TaskRepository;
import br.com.leticiasantanna.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/create")
    public ResponseEntity createTask(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        var userId = request.getAttribute("userId");
        taskModel.setUserId((UUID) userId);


        var currentDate = LocalDateTime.now();
        if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getFinishAt()) ){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("A data de início e termino não pode ser menor que a data atual!");
        }

        if(taskModel.getStartAt().isAfter(taskModel.getFinishAt())){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("A data de início e termino não pode ser menor que a data de termino!");
        }

        var task = taskRepository.save(taskModel);

        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/find")
    public List<TaskModel> listAllTasksByUserId(HttpServletRequest request) {
        var userId = request.getAttribute("userId");

        return taskRepository.findByIdUser((UUID) userId);
    }

    @PutMapping ("/{id}")
    public ResponseEntity updateTaskById(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {
      var task = taskRepository.findById(id).orElse(null);
      var userId = request.getAttribute("userId");


        assert task != null;
        if(task.getUserId() != (userId)) {
          return ResponseEntity
                  .status(HttpStatus.BAD_REQUEST)
                  .body("Alteração permitida apenas para o dono da tarefa!");
      }

        Utils.copyNonNullProperties(taskModel, task);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskRepository.save(task));
    }
}
