package com.example.householdaccount.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.householdaccount.entity.Expenditure;
import com.example.householdaccount.entity.SearchResultIncome;
import com.example.householdaccount.entity.Expenditure.ExpenditureNoVO;
import com.example.householdaccount.entity.SearchResultExpenditure;

public interface SearchExpenditureHouseholdRepository extends JpaRepository<SearchResultExpenditure, ExpenditureNoVO> {
	@Query(value = "SELECT e.EXPENDITURE_NO, e.EXPENDITURE_DATE, e.EXPENDITURE_EXPENSE_ITEM_NAME, e.AMOUNT, e.NOTE FROM EXPENDITURE AS e WHERE e.EXPENDITURE_NO LIKE %:balanceCode% ORDER BY e.EXPENDITURE_NO ASC", nativeQuery = true)
	public List<SearchResultExpenditure> findExpenditureByBalanceCode(String balanceCode);
	
	public List<SearchResultExpenditure> findAll(Specification<SearchResultExpenditure> findAllSpecification);
}
