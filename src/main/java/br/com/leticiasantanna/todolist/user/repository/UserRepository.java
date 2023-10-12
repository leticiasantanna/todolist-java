package br.com.leticiasantanna.todolist.user.repository;

import br.com.leticiasantanna.todolist.user.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
  UserModel findByUsername(String username);
}
