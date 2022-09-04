package com.example.demo.controller;

import com.example.demo.dto.ExpenseDto;
import com.example.demo.dto.ExpenseFilterDto;
import com.example.demo.service.ExpenseService;
import com.example.demo.validator.ExpenseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("expense")
@RequiredArgsConstructor
@Validated
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping("/list")
    public String listForm(Model model)
    {
        List<ExpenseDto> allExpense = expenseService.getAllExpense();
        model.addAttribute("expenseList",allExpense);
        model.addAttribute("filter",new ExpenseFilterDto());
        String s = expenseService.totalExpense(allExpense);
        model.addAttribute("totalAmount",s);
        return "expense-list";
    }

    @GetMapping("/create")
    public String showForm(ModelMap modelMap)
    {
        modelMap.addAttribute("expenseDto", new ExpenseDto());
        return "expense-form";
    }

    @GetMapping("/update")
    public String showForm(@RequestParam("id")  String id, Model model)
    {
        ExpenseDto expenseById = expenseService.getExpenseById(id);
        model.addAttribute("expenseDto",expenseById);
        return "expense-form";
    }

    @GetMapping("/delete")
    public String deleteForm(@RequestParam("id") String id)
    {
        expenseService.removeExpense(id);
        return "redirect:/expense/list";
    }

    @PostMapping("/processForm")
    public String processForm(
            @Valid @ModelAttribute("expenseDto") ExpenseDto expenseDto,
            BindingResult bindingResult
    ) throws ParseException {

        new ExpenseValidator().validate(expenseDto,bindingResult);

        if (bindingResult.hasErrors())
        {
            return "expense-form";
        }
        ExpenseDto expenseDto1 = expenseService.saveExpenseDetails(expenseDto);
        return "redirect:/expense/list";
    }



}