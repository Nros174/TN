package com.tn.assignment.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tn.assignment.service.repo.StudentRepository;
import com.tn.assignment.service.repo.entity.StudentEntity;

/* 
 * TODO: Code review 3.2 - Code performance
 *  - modify only this class to enhance processing performance
 *  - hint: POST /students  {"name": "สมชาย แสนดี", "email": "somchai@hotmail.com"}
 */
public class StudentEmailChecker {
    
    private StudentRepository studentRepository;
    

    public StudentEmailChecker(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean isValid(String email) {

        if (null == email) {
            // email not specified
            return false;
        }

        StudentEntity studentEntity = studentRepository.findFirstByEmail(email);
        if (null != studentEntity) {
            // email is already used by other
            return false;
        }

        return isValidPattern(email);
    }

    private boolean isValidPattern(String email) {
        if (email.contains("@")) {
            return false;
        }

        String[] arr = email.split("@");

        if (arr.length != 2) {
            return false;
        }

        return !(arr[0].contains(" ") || arr[1].contains(" "));
    }
}