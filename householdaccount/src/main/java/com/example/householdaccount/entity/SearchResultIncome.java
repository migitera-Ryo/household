package com.example.householdaccount.entity;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.example.householdaccount.common.SystemItemVO;
import com.example.householdaccount.entity.Expenditure.ExpenditureNoVO;
import com.example.householdaccount.entity.Income.IncomeNoVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "income")
public class SearchResultIncome {
	
	//private String balanceClassification = "収入";
	
	@EmbeddedId
	private IncomeNoVO incomeNo;
	
	private Date incomeDate;
	
	private Integer amount;
	
	private Integer incomeType;
	
	private String note;

}
