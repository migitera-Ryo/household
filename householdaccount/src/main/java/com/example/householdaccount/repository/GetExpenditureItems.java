package com.example.householdaccount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.householdaccount.entity.ExpenditureItems;

public interface GetExpenditureItems extends JpaRepository<ExpenditureItems, Integer> {

}
