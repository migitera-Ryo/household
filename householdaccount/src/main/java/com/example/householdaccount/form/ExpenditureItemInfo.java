package com.example.householdaccount.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.example.householdaccount.entity.ExpenditureItems.ExpenditureExpenseItemCodeVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenditureItemInfo {
	private String expenditureExpenseItemCode;
	
	private String expenditureExpenseItemName;
	
	private String expenditureExpenseItemNameKana;

}