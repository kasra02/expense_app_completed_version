package com.example.demo.controller;

import com.example.demo.dto.ExpenseDto;
import com.example.demo.dto.ExpenseFilterDto;
import com.example.demo.service.ExpenseService;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/filterExpenses")
@RequiredArgsConstructor
public class ExpenseFilterController {

    private final ExpenseService expenseService;

    @GetMapping
    public String filterExpense(@ModelAttribute("filter") ExpenseFilterDto expenseFilterDto, Model model) throws ParseException {

        List<ExpenseDto> filteredExpenses = expenseService.getFilteredExpenses(expenseFilterDto);
        model.addAttribute("expenseList",filteredExpenses);

        String s = expenseService.totalExpense(filteredExpenses);
        model.addAttribute("totalAmount",s);

        return "expense-list";
    }

}
