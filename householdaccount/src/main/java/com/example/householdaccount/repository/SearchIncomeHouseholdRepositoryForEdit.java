package com.example.householdaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.householdaccount.entity.Income.IncomeNoVO;
import com.example.householdaccount.entity.SearchResultIncome;
import com.example.householdaccount.entity.SearchResultIncomeForEdit;

public interface SearchIncomeHouseholdRepositoryForEdit extends JpaRepository<SearchResultIncomeForEdit, IncomeNoVO> {
	@Query(value = "SELECT i.INCOME_NO, i.INCOME_DATE, i.INCOME_TYPE, i.AMOUNT, i.NOTE ,i.VERSION FROM INCOME AS i WHERE i.INCOME_NO = :balanceCode ORDER BY i.INCOME_NO ASC", nativeQuery = true)
	public SearchResultIncomeForEdit findIncomeByBalanceCode(String balanceCode);
}
