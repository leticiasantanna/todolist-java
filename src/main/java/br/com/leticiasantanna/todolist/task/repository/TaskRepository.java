package br.com.leticiasantanna.todolist.task.repository;

import br.com.leticiasantanna.todolist.task.entity.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
}
