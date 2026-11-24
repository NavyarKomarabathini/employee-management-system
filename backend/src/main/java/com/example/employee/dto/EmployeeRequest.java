package com.example.employee.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record EmployeeRequest(

    @NotBlank
    @Size(max = 100)
    String name,

    @NotBlank
    @Email
    @Size(max = 150)
    String email,

    @NotNull
    @Positive
    Long departmentId,

    @NotNull
    @PositiveOrZero
    @Digits(integer = 10, fraction = 2)
    BigDecimal salary

) {
}