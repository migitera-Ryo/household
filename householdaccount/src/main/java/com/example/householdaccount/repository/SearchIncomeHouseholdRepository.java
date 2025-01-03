package com.example.householdaccount.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.householdaccount.entity.Income;
import com.example.householdaccount.entity.Income.IncomeNoVO;
import com.example.householdaccount.entity.SearchResultIncome;

public interface SearchIncomeHouseholdRepository extends JpaRepository<SearchResultIncome, IncomeNoVO>{
	@Query(value = "SELECT i.INCOME_NO, i.INCOME_DATE, i.INCOME_TYPE, i.AMOUNT, i.NOTE , i.DELETE_FRAG FROM INCOME AS i WHERE i.INCOME_NO LIKE %:balanceCode% and i.DELETE_FRAG != true ORDER BY i.INCOME_NO ASC", nativeQuery = true)
	public List<SearchResultIncome> findIncomeByBalanceCode(String balanceCode);
	

	public List<SearchResultIncome> findAll(Specification<SearchResultIncome> findAllSpecification);

}
