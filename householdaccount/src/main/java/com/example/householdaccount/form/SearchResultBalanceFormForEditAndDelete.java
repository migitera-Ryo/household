package com.example.householdaccount.form;

import java.util.Date;

import lombok.Data;
@Data
public class SearchResultBalanceFormForEditAndDelete {
	private String balanceType;
	private String balanceCode;
	private Integer amount; 
	private Date balanceDate;
	private Integer incomeType;
	private String expenditureExpenseItemName;
	private String note;
	private Integer version;
}
