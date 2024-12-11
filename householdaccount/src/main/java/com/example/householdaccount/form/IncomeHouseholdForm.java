package com.example.householdaccount.form;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IncomeHouseholdForm {
	
	@NotNull
	@Digits(integer = 8, fraction = 0)
	@Positive
	private Integer amount;
	
	@NotNull
	private Integer incomeType;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date incomeDate;
	
	@Size(max = 200)
	private String note;

}
