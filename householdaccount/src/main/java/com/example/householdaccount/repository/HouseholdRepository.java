package com.example.householdaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.householdaccount.entity.CreateBalanceInfo;
import com.example.householdaccount.entity.Income;

public interface HouseholdRepository extends JpaRepository<Income,Integer>{

}
