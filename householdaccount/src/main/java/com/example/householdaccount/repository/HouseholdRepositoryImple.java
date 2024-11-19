package com.example.householdaccount.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.example.householdaccount.entity.CreateBalanceInfo;
import com.example.householdaccount.repository.mybatis.HouseholdMapper;

@Repository
public class HouseholdRepositoryImple implements HouseholdRepository {
	private final SqlSessionTemplate sqlSessionTemplate;

    public HouseholdRepositoryImple(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
	
	@Override
	public void insert(CreateBalanceInfo createbalanceinfo) {
		this.sqlSessionTemplate.getMapper(HouseholdMapper.class).add(createbalanceinfo);

	}

}
