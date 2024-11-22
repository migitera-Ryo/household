package com.example.householdaccount.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.householdaccount.entity.ExpenditureItems;
import com.example.householdaccount.entity.Income;
import com.example.householdaccount.form.InputHouseholdInfo;
import com.example.householdaccount.service.HouseholdServices;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class HouseholeController {
	@Autowired
	HouseholdServices householdServices;
	
	@GetMapping("/expenseItems")
    public List<ExpenditureItems> getUsers() {
		List<ExpenditureItems> list = householdServices.expenditureItemsInfo();
        // ユーザーリストを返す
		System.out.println("999999999999999999999999999999");
		System.out.println(list);
        return list;
    }
	
	@RequestMapping(value = "/recieve", method = RequestMethod.POST) 
	public void incomeCreate( @RequestBody  InputHouseholdInfo inputhouseholdinfo ){
		
		System.out.println(inputhouseholdinfo.getAmount());
		System.out.println(inputhouseholdinfo.getIncomeDate());
		System.out.println("aaaaaaaaaaaa");
		
		//createincomeinfo.setIncomeNo("I240400001");
		//createincomeinfo.setInitialCreateDateAndTime(new Date());
		//createincomeinfo.setLastUpdateDateAndTime(new Date());
		//createincomeinfo.setInitialCreateUserCode("a");
		//createincomeinfo.setLastUpdateUserCode("a");
		//createincomeinfo.setVersion(0);
		
		//System.out.println(income.getInitialCreateDateAndTime());
		
		
		householdServices.postCreateIncomeInfo(inputhouseholdinfo);
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiii");
	}
	
	@RequestMapping(value = "/expenditure", method = RequestMethod.POST) 
	public void expenditure( @RequestBody  InputHouseholdInfo inputhouseholdinfo ){
		
		System.out.println(inputhouseholdinfo.getAmount());
		System.out.println(inputhouseholdinfo.getIncomeDate());
		System.out.println("aaaaaaaaaaaa");
		
		//createincomeinfo.setIncomeNo("I240400001");
		//createincomeinfo.setInitialCreateDateAndTime(new Date());
		//createincomeinfo.setLastUpdateDateAndTime(new Date());
		//createincomeinfo.setInitialCreateUserCode("a");
		//createincomeinfo.setLastUpdateUserCode("a");
		//createincomeinfo.setVersion(0);
		
		//System.out.println(income.getInitialCreateDateAndTime());
		
		
		householdServices.postCreateIncomeInfo(inputhouseholdinfo);
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiii");
	}

}
