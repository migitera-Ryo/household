package com.example.householdaccount.form;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenditureHouseholdForm {
	
	@NotNull
	@Digits(integer = 8, fraction = 0)
	@Positive
	private Integer amount;
	
	@NotNull
	@Size(max = 5)
	private String expenditureItemCode;
	
	private String expenditureItemName;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expenditureDate;
	
	@Size(max = 200)
	private String note;


}
