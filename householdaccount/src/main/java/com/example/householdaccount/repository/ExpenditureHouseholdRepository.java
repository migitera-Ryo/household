package com.example.householdaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.householdaccount.entity.Expenditure;
import com.example.householdaccount.entity.Expenditure.ExpenditureNoVO;

public interface ExpenditureHouseholdRepository extends JpaRepository<Expenditure, ExpenditureNoVO> {
	

}
