package com.example.householdaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.householdaccount.entity.Expenditure.ExpenditureNoVO;
import com.example.householdaccount.entity.SearchResultExpenditure;
import com.example.householdaccount.entity.SearchResultExpenditureForEdit;

public interface SearchExpenditureHouseholdRepositoryForEdit extends JpaRepository<SearchResultExpenditureForEdit, ExpenditureNoVO> {
	//試作
		@Query(value = "SELECT e.EXPENDITURE_NO, e.EXPENDITURE_DATE, e.EXPENDITURE_EXPENSE_ITEM_NAME, e.AMOUNT, e.NOTE, e.VERSION FROM EXPENDITURE AS e WHERE e.EXPENDITURE_NO = :balanceCode ORDER BY e.EXPENDITURE_NO ASC", nativeQuery = true)
		public SearchResultExpenditureForEdit findExpenditureByBalanceCode(String balanceCode);
}
