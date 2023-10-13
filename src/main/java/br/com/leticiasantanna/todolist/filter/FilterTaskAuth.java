package br.com.leticiasantanna.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.leticiasantanna.todolist.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    UserRepository userRepository;

    @Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain )
            throws ServletException, IOException {

     var authorization = request.getHeader("Authorization");

     var passwordValid = authorization.substring("Basic".length()).trim();

     byte[] auth = Base64.getDecoder().decode(passwordValid);
     var authToString = new String(auth);

     String[] credentials = authToString.split(":");
     String username = credentials[0];
     String password = credentials[1];

     //Validar usuário se existe
       var user = userRepository.findByUsername(username);

       if(Objects.isNull(user)) {
           response.sendError(401);
       } else {
           //validar senha
          var verifyPassword = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
          if(verifyPassword.verified) {
              filterChain.doFilter(request, response);
          } else {
              response.sendError(401);
          }
       }

        filterChain.doFilter(request, response);
    }
}
