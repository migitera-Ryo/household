package com.example.householdaccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.householdaccount.entity.CreateIncomeInfo;
import com.example.householdaccount.repository.HouseholdRepository;

@Service
@Transactional
public class HouseholdServices {
	@Autowired
	private HouseholdRepository householdRepository;
	
	public CreateIncomeInfo postCreateIncomeInfo(CreateIncomeInfo createincomeinfo) {
        return householdRepository.save(createincomeinfo);
    }

}
