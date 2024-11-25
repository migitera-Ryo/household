package com.example.householdaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.householdaccount.entity.Expenditure;

public interface ExpenditureHouseholdRepository extends JpaRepository<Expenditure, Integer> {
	

}
