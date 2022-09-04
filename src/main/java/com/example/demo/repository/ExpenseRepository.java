package com.example.demo.repository;

import com.example.demo.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> getExpenseByExpenseId(String id);
    List<Expense> findExpenseByNameContainingIgnoreCaseAndDateBetweenAndUserId(String name, Date startDate, Date endDate, Long id);

    List<Expense> findExpenseByUserId(Long id);


}