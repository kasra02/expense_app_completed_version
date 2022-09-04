package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ExpenseFilterDto {
    private String keyword;
    private String sortBy;
    private String startDate;
    private String endDate;
}
