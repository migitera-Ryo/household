package com.example.householdaccount.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "income")
public class CreateIncomeInfo {
	@Id
    @GeneratedValue
	private String incomeNo;
	
	@Column(nullable = false)
	private int amount;
	
	@Column(nullable = false)
	private int selectedIncomeType;
	
	@Column(nullable = false)
	private Date balanceDate;
	
	@Column(nullable = true)
	private String note;
	
	@Column(nullable = false)
	private boolean deleteFrag;
	
	@Column(nullable = false)
	private Date initialCreateDateAndTime;
	
	@Column(nullable = false)
	private Date lastUpdateDateAndTime;
	
	@Column(nullable = false)
	private String initialCreateUserCode;
	
	@Column(nullable = false)
	private String lastUpdateUserCode;
	
	@Column(nullable = false)
	private int version;

}
