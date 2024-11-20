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

import com.example.householdaccount.entity.CreateBalanceInfo;
import com.example.householdaccount.entity.CreateIncomeInfo;
import com.example.householdaccount.service.HouseholdServices;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class HouseholeController {
	@Autowired
	HouseholdServices householdServices;
	
	@GetMapping("/users")
    public List<String> getUsers() {
		List<String> list = Arrays.asList("AA", "BB", "CC");
        // ユーザーリストを返す
		System.out.println("aaaaaaaaaaaa");
        return list;
    }
	
	@RequestMapping(value = "/recieve", method = RequestMethod.POST) 
	public void recieve( @RequestBody  CreateIncomeInfo createincomeinfo ){
		
		System.out.println(createincomeinfo.getAmount());
		System.out.println(createincomeinfo.getBalanceDate());
		System.out.println("aaaaaaaaaaaa");
		
		createincomeinfo.setIncomeNo("I240400001");
		createincomeinfo.setInitialCreateDateAndTime(new Date());
		createincomeinfo.setLastUpdateDateAndTime(new Date());
		createincomeinfo.setInitialCreateUserCode("a");
		createincomeinfo.setLastUpdateUserCode("a");
		createincomeinfo.setVersion(0);
		
		System.out.println(createincomeinfo.getInitialCreateDateAndTime());
		
		
		householdServices.postCreateIncomeInfo(createincomeinfo);
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiii");
	}

}
