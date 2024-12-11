package com.example.householdaccount.entity;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.householdaccount.entity.Expenditure.ExpenditureNoVO;
import com.example.householdaccount.entity.Income.IncomeNoVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "expenditure")
public class SearchResultExpenditure {
	@EmbeddedId
	private ExpenditureNoVO expenditureNo;
	
	private Date expenditureDate;
	
	private Integer amount;
	
	private String expenditureExpenseItemName;
	
	private String note;
}
