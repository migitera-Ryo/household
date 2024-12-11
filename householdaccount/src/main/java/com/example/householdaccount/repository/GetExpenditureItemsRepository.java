package com.example.householdaccount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.householdaccount.entity.Expenditure;
import com.example.householdaccount.entity.ExpenditureItem;
import com.example.householdaccount.entity.ExpenditureItem.ExpenditureExpenseItemCodeVO;

public interface GetExpenditureItemsRepository extends JpaRepository<ExpenditureItem, ExpenditureExpenseItemCodeVO> {
	public ExpenditureItem findByExpenditureExpenseItemCode(ExpenditureExpenseItemCodeVO Code);

}
