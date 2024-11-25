package com.example.householdaccount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.householdaccount.entity.Expenditure;
import com.example.householdaccount.entity.ExpenditureItems;

public interface GetExpenditureItemsRepository extends JpaRepository<ExpenditureItems, String> {
	public ExpenditureItems findByExpenditureExpenseItemCode(String Code);

}
