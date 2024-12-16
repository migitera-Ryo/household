package com.example.householdaccount.form;

import java.util.Date;

import lombok.Data;

@Data
public class SearchResultBalanceForm {
	private String balanceType;
	private String balanceCode;
	private Integer amount; 
	private Date balanceDate;
	private int incomeType;
	private String expenditureExpenseItemName;
	private String note;

}
