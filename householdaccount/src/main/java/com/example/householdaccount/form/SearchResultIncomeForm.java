package com.example.householdaccount.form;

import java.util.Date;

import javax.persistence.EmbeddedId;

import com.example.householdaccount.entity.Income.IncomeNoVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultIncomeForm {
	
	private String balanceClassification = "収入";
	
	private IncomeNoVO incomeNo;
	
	private Date incomeDate;
	
	private Integer amount;
	
	private String incomeType;
	
	private String note;

}
