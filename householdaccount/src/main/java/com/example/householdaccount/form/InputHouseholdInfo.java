package com.example.householdaccount.form;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InputHouseholdInfo {
	
	private int amount;
	
	
	private int incomeType;
	
	
	private Date incomeDate;
	
	
	private String note;

}
