package br.com.leticiasantanna.todolist.task.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_task")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;
    @Column(length = 30)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime finishAt;
    private String priority;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private UUID userId;
}
