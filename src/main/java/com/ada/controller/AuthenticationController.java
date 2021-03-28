package com.ada.controller;


import com.ada.dto.ResponseDTO;
import com.ada.entity.Student;
import com.ada.repository.StudentRepository;
import com.ada.service.SessionService;
import com.ada.service.StudentServiceImpl;
import com.ada.utils.TokenGeneration;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Log
@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentServiceImpl userService;

    @Autowired
    private TokenGeneration generation;

    @Autowired
    private SessionService sessionService;

    @PostMapping("/register")
     public ResponseDTO register(@RequestBody Student student, HttpServletRequest request){
        ResponseDTO responseDTO = ResponseDTO.getInstance();
         Optional<Student> user1 = studentRepository.findByEmail(student.getEmail());
         if (user1.isPresent()) {
             log.info("Email is duplicated");
          return responseDTO.error("This email is already used during registration");
         }
         student.setTokenValidated(false);
         String token = generation.generateToken();

         student.setToken(token);
         userService.save(student);
         return ResponseDTO.getInstance().success("Successfully registered,use this token for the first time only when you login -> " + token);
     }


     @PostMapping("/login")
    public ResponseDTO login(@RequestBody Student student, @RequestParam(required = false) String token, HttpServletRequest  servletRequest){
     Optional<Student> userOptional = userService.login(student.getEmail(), student.getPassword());
     if (!userOptional.isPresent()) {
         log.info("Wrong credentials");
       return   ResponseDTO.getInstance().error("Wrong credentials");
     }
         String validatedToken =userOptional.get().getToken();

         if (userOptional.get().getTokenValidated()==true) {
             log.info("Logged successfully");
             servletRequest.getSession().setAttribute("student", userOptional.get());
             return ResponseDTO.getInstance().success("You logged in successfully");
         }

         if (validatedToken.equals(token)){
             userService.updateToken(true);
             log.info("Token is updated to true state in table");
             servletRequest.getSession().setAttribute("student", userOptional.get());
             return ResponseDTO.getInstance().success("You logged in successfully");
         }

         return ResponseDTO.getInstance().error("Token is not correct or token do not be send for the first time ");
     }

    @GetMapping("/logout")
    public ResponseDTO logout(HttpServletRequest servletRequest){
        servletRequest.getSession().invalidate();
        log.info("Session is terminated");
        return ResponseDTO.getInstance().success("Logged out successfully");
    }

}
