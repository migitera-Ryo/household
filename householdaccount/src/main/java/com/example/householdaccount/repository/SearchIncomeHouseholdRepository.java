package com.example.householdaccount.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.householdaccount.entity.Income.IncomeNoVO;
import com.example.householdaccount.entity.SearchResultIncome;

public interface SearchIncomeHouseholdRepository extends JpaRepository<SearchResultIncome, IncomeNoVO> {
	@Query(value = "SELECT i.INCOME_NO, i.INCOME_DATE, i.INCOME_TYPE, i.AMOUNT, i.NOTE FROM INCOME AS i WHERE i.INCOME_NO LIKE %:balanceCode% ORDER BY i.INCOME_NO ASC", nativeQuery = true)
	public List<SearchResultIncome> findIncomeByBalanceCode(String balanceCode);
	
//	@Query(value = "SELECT i.INCOME_NO, i.INCOME_DATE, i.INCOME_TYPE, i.AMOUNT, i.NOTE FROM INCOME AS i WHERE i.INCOME_DATE > incomeFromDate OR i.INCOME_DATE < incomeToDate OR i.AMOUNT > incomeFromAmount OR i.AMOUNT < incomeToAmount OR i.INCOME_TYPE = incomeType OR i.NOTE LIKE %:incomeNote%", nativeQuery = true)
//	public List<SearchResultIncome> detailFindIncome(String incomeFromDate, String incomeToDate,String incomeFromAmount,String incomeToAmount,String incomeType,String incomeNote);

	@Query(value = "SELECT i.INCOME_NO, i.INCOME_DATE, i.INCOME_TYPE, i.AMOUNT, i.NOTE FROM INCOME AS i WHERE i.INCOME_DATE > ?1 OR i.INCOME_DATE < ?2 OR i.AMOUNT > ?3 OR i.AMOUNT < ?4 OR i.INCOME_TYPE = ?5 OR i.NOTE LIKE %:?6%", nativeQuery = true)
	public List<SearchResultIncome> detailFindIncome(Specification<SearchResultIncome> findAllSpecification);
}
