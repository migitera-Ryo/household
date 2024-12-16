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
	
}
