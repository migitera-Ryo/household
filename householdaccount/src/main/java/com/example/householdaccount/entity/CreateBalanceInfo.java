package com.example.householdaccount.entity;

import java.util.Date;

public class CreateBalanceInfo {
	private String selectedButtonNumber;
	private int amount;
	private String selectedIncomeType;
	private String balanceDate;
	private String note;
	
	public CreateBalanceInfo( String selectedButtonNumber, int amount, String selectedIncomeType, String balanceDate, String note){
		this.selectedButtonNumber = selectedButtonNumber;
		this.amount = amount;
		this.selectedIncomeType = selectedIncomeType;
		this.balanceDate = balanceDate;
		this.note = note;
	}

	public String getSelectedButtonNumber() {
		return selectedButtonNumber;
	}

	public void setSelectedButtonNumber(String selectedButtonNumber) {
		this.selectedButtonNumber = selectedButtonNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getSelectedIncomeType() {
		return selectedIncomeType;
	}

	public void setSelectedIncomeType(String selectedIncomeType) {
		this.selectedIncomeType = selectedIncomeType;
	}

	public String getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
