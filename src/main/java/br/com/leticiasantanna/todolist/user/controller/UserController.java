package br.com.leticiasantanna.todolist.user.controller;

import br.com.leticiasantanna.todolist.user.entity.UserModel;
import br.com.leticiasantanna.todolist.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody UserModel userModel) {
    var user = userRepository.findByUsername(userModel.getUsername());

    if(user != null) {
      return ResponseEntity
              .status(HttpStatusCode.valueOf(400))
              .body("Usuário já cadastrado!");
    }

    var userCreated = userRepository.save(userModel);

      return ResponseEntity
              .status(HttpStatusCode.valueOf(201))
              .body(userCreated);
  }
}
