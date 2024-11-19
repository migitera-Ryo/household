package com.example.householdaccount.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.householdaccount.entity.CreateBalanceInfo;
import com.example.householdaccount.service.HouseholdService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class HouseholeController {
	private final HouseholdService service;
	
	public HouseholeController(HouseholdService service) {
        this.service = service;
    }
	
	
	
	@GetMapping("/users")
    public List<String> getUsers() {
		List<String> list = Arrays.asList("AA", "BB", "CC");
        // ユーザーリストを返す
		System.out.println("aaaaaaaaaaaa");
        return list;
    }
	
	@RequestMapping(value = "/recieve", method = RequestMethod.POST) 
	public void recieve( @RequestBody  CreateBalanceInfo createbalanceinfo ){
		
		System.out.println(createbalanceinfo.getAmount());
		System.out.println(createbalanceinfo.getBalanceDate());
		System.out.println("aaaaaaaaaaaa");
		
		createbalanceinfo.setIncomeNo("I240400001");
		createbalanceinfo.setInitialCreateDateAndTime(new Date());
		createbalanceinfo.setLastUpdateDateAndTime(new Date());
		createbalanceinfo.setInitialCreateUserCode("a");
		createbalanceinfo.setLastUpdateUserCode("a");
		createbalanceinfo.setVersion(0);
		
		
		this.service.add(createbalanceinfo);
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiii");
	}

}
