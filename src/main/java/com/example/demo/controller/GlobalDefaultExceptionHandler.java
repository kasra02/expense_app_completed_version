package com.example.demo.controller;

import com.example.demo.exception.ExpenseNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(ExpenseNotFoundException.class)
    public String handleExpenseNotFoundException(HttpServletRequest request, ExpenseNotFoundException ex, Model model)
    {
        model.addAttribute("notFound",true);
        model.addAttribute("message",ex.getMessage());
        return "response";

    }
}
