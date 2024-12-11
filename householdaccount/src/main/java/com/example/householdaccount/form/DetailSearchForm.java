package com.example.householdaccount.form;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailSearchForm {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fromDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date toDate;
	
	@Digits(integer = 8, fraction = 0)
	@Positive
	private Integer fromamount;
	
	@Digits(integer = 8, fraction = 0)
	@Positive
	private Integer toamount;
	
	private Integer incomeType;
	
	private String expenditureItemName;
	
	@Size(max = 200)
	private String note;

}
