package com.example.householdaccount.form;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenditureHouseholdInfo {
	private int amount;
	
	private String expenditureItemCode;
	
	private String expenditureItemName;
	
	private Date expenditureDate;
	
	private String note;


}
