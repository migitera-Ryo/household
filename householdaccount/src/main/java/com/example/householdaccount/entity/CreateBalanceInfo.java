package com.example.householdaccount.entity;

import java.util.Date;

public class CreateBalanceInfo {
	private String selectedButtonNumber;
	private int amount;
	private String selectedIncomeType;
	private String balance_date;
	private String note;
	
	public CreateBalanceInfo( String selectedButtonNumber, int amount, String selectedIncomeType, String balance_date, String note){
		this.selectedButtonNumber = selectedButtonNumber;
		this.amount = amount;
		this.selectedIncomeType = selectedIncomeType;
		this.balance_date = balance_date;
		this.note = note;
	}

	public String getIncome_no() {
		return selectedButtonNumber;
	}

	public void setIncome_no(String selectedButtonNumber) {
		this.selectedButtonNumber = selectedButtonNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getIncome_type() {
		return selectedIncomeType;
	}

	public void setIncome_type(String selectedIncomeType) {
		this.selectedIncomeType = selectedIncomeType;
	}

	public String getIncome_date() {
		return balance_date;
	}

	public void setIncome_date(String balance_date) {
		this.balance_date = balance_date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
