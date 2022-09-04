package com.example.demo.service;

import com.example.demo.dto.ExpenseDto;
import com.example.demo.dto.ExpenseFilterDto;
import com.example.demo.entity.Expense;
import com.example.demo.entity.User;
import com.example.demo.exception.ExpenseNotFoundException;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.util.DateTimeUtil;
import com.ibm.icu.text.NumberFormat;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public List<ExpenseDto> getAllExpense() {
        User loggedInUser = userService.getLoggedInUser();
        List<Expense> all = expenseRepository.findExpenseByUserId(loggedInUser.getId());
        List<ExpenseDto> expenseDtos = all.stream().map(this::mapToDTO).toList();
        return expenseDtos;
    }


    public ExpenseDto saveExpenseDetails(ExpenseDto expenseDto) throws ParseException {
        Expense expense = mapToEntity(expenseDto);
        expense.setUser(userService.getLoggedInUser());
        expense = expenseRepository.save(expense);
        return mapToDTO(expense);
    }

    public ExpenseDto getExpenseById(String id){
        Expense expense = getExpense(id);
        return mapToDTO(expense);
    }

    private Expense getExpense(String id)
    {
        return expenseRepository
                .getExpenseByExpenseId(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found for the id:"+id));
    }

    private Expense mapToEntity(ExpenseDto expenseDto) throws ParseException {
        Expense expense = modelMapper.map(expenseDto, Expense.class);

        if (expense.getId() == null){
            expense.setExpenseId(UUID.randomUUID().toString());
        }

        expense.setDate(DateTimeUtil.convertStringToDate(expenseDto.getDateString()));
        return expense;
    }


    private ExpenseDto mapToDTO(Expense expense) {
        ExpenseDto expenseDto = modelMapper.map(expense, ExpenseDto.class);
        expenseDto.setDateString(DateTimeUtil.convertDateToString(expense.getDate()));
        return expenseDto;
    }

    public void removeExpense(String id) {
        Expense expense = getExpense(id);
        expenseRepository.delete(expense);
    }

    public List<ExpenseDto> getFilteredExpenses(ExpenseFilterDto expenseFilterDto) throws ParseException {
        String startDateString = expenseFilterDto.getStartDate();
        String endDateString = expenseFilterDto.getEndDate();
        Date startDate = !startDateString.isEmpty() ? DateTimeUtil.convertStringToDate(startDateString) : new Date(0);
        Date endDate = !endDateString.isEmpty() ? DateTimeUtil.convertStringToDate(endDateString) : new Date(System.currentTimeMillis());

        User loggedInUser = userService.getLoggedInUser();
        List<Expense> expenseList = expenseRepository
                .findExpenseByNameContainingIgnoreCaseAndDateBetweenAndUserId(
                        expenseFilterDto.getKeyword(),
                        startDate,
                        endDate,
                        loggedInUser.getId()
                );
        List<ExpenseDto> filteredList = expenseList.stream().map(this::mapToDTO).collect(Collectors.toList());


        if (expenseFilterDto.getSortBy().equals("amount")){
            filteredList.sort((o1, o2) -> o2.getAmount().compareTo(o1.getAmount()));
        }else {
            filteredList.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        }
        return filteredList;
    }

    public String totalExpense(List<ExpenseDto> expenseDtos)
    {
        BigDecimal sum = new BigDecimal(0);
        BigDecimal reduce = expenseDtos.stream().map(x -> x.getAmount().add(sum))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(new Locale("fa", "ir"));
        return currencyInstance.format(reduce);
    }


}