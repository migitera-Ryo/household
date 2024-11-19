package com.example.householdaccount.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.householdaccount.entity.CreateBalanceInfo;
import com.example.householdaccount.repository.HouseholdRepository;

@Service
public class HouseholdServiceImpl implements HouseholdService {
	private final HouseholdRepository repository;

    public HouseholdServiceImpl(HouseholdRepository repository) {
        this.repository = repository;
    }
	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void add(CreateBalanceInfo createbalanceinfo) {
		 this.repository.insert(createbalanceinfo);

	}

}
