package com.example.householdaccount.entity;

import java.util.Date;

public class CreateBalanceInfo {
	private String incomeNo;
	private int amount;
	private int selectedIncomeType;
	private Date balanceDate;
	private String note;
	private boolean deleteFrag;
	private Date initialCreateDateAndTime;
	private Date lastUpdateDateAndTime;
	private String initialCreateUserCode;
	private String lastUpdateUserCode;
	private int version;
	
	
	public CreateBalanceInfo(String incomeNo,  int amount, int selectedIncomeType, Date balanceDate, 
			String note, boolean deleteFrag, Date initialCreateDateAndTime, Date lastUpdateDateAndTime, String initialCreateUserCode, String lastUpdateUserCode,int version){
		this.incomeNo = incomeNo;
		this.amount = amount;
		this.selectedIncomeType = selectedIncomeType;
		this.balanceDate = balanceDate;
		this.note = note;
		this.deleteFrag = deleteFrag;
		this.initialCreateDateAndTime = initialCreateDateAndTime;
		this.lastUpdateDateAndTime = lastUpdateDateAndTime;
		this.initialCreateUserCode = initialCreateUserCode;
		this.lastUpdateUserCode = lastUpdateUserCode;
		this.version = version;
	}

	public String getIncomeNo() {
		return incomeNo;
	}

	public void setIncomeNo(String incomeNo) {
		this.incomeNo = incomeNo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getSelectedIncomeType() {
		return selectedIncomeType;
	}

	public void setSelectedIncomeType(int selectedIncomeType) {
		this.selectedIncomeType = selectedIncomeType;
	}

	public Date getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public boolean isDeleteFrag() {
		return deleteFrag;
	}

	public void setDeleteFrag(boolean deleteFrag) {
		this.deleteFrag = deleteFrag;
	}

	public Date getInitialCreateDateAndTime() {
		return initialCreateDateAndTime;
	}

	public void setInitialCreateDateAndTime(Date initialCreateDateAndTime) {
		this.initialCreateDateAndTime = initialCreateDateAndTime;
	}

	public Date getLastUpdateDateAndTime() {
		return lastUpdateDateAndTime;
	}

	public void setLastUpdateDateAndTime(Date lastUpdateDateAndTime) {
		this.lastUpdateDateAndTime = lastUpdateDateAndTime;
	}

	public String getInitialCreateUserCode() {
		return initialCreateUserCode;
	}

	public void setInitialCreateUserCode(String initialCreateUserCode) {
		this.initialCreateUserCode = initialCreateUserCode;
	}

	public String getLastUpdateUserCode() {
		return lastUpdateUserCode;
	}

	public void setLastUpdateUserCode(String lastUpdateUserCode) {
		this.lastUpdateUserCode = lastUpdateUserCode;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
