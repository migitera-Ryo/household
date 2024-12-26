package com.example.householdaccount.entity;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.householdaccount.entity.Income.IncomeNoVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "income")
public class SearchResultIncomeForEditAndDelete {
	@EmbeddedId
	private IncomeNoVO incomeNo;
	
	private Date incomeDate;
	
	private Integer amount;
	
	private Integer incomeType;
	
	private String note;
	
	private Integer version;
}
