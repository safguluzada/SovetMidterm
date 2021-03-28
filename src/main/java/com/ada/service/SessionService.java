package com.ada.service;

import com.ada.entity.Student;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Log
@Service
public class SessionService {

    public Student getSessionInfo(HttpServletRequest httpServletRequest){
        Student student =  (Student) httpServletRequest.getSession().getAttribute("student");
        if (student !=null){
            return student;
        }
        log.info("No session");
        return null;
    }
}
