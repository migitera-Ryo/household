package com.example.householdaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.householdaccount.entity.CreateBalanceInfo;
import com.example.householdaccount.entity.CreateIncomeInfo;

public interface HouseholdRepository extends JpaRepository<CreateIncomeInfo,Integer>{

}
