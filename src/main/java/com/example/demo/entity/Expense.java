package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Expense {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String expenseId;
    private String name;
    private String description;
    private BigDecimal amount;
    private Date date;


    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;


}
