package com.example.householdaccount.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.householdaccount.entity.ExpenditureItem;
import com.example.householdaccount.entity.Income;
import com.example.householdaccount.entity.ExpenditureItem.ExpenditureExpenseItemCodeVO;
import com.example.householdaccount.entity.Income.IncomeNoVO;
import com.example.householdaccount.entity.SearchResultIncome;

public interface IncomeHouseholdRepository extends JpaRepository<Income,IncomeNoVO>{

	List<Income> findAll(Specification<Income> findAllSpecification);
	
//	@Query(value = "UPDATE INCOME AS i SET i.INCOME_NO = income.incomeNo, i.INCOME_DATE = income.incomeDate, i.INCOME_TYPE = income.incomeType, i.AMOUNT = income.amount, i.NOTE = income.note, i.VERSION= income.version WHERE i.INCOME_NO = :income.incomeNo", nativeQuery = true)
//	public Income update(Income income);
	
}
