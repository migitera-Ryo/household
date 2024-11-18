package com.example.householdaccount.controller;

import java.util.Arrays;
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

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class HouseholeController {
	@GetMapping("/users")
    public List<String> getUsers() {
		List<String> list = Arrays.asList("AA", "BB", "CC");
        // ユーザーリストを返す
		System.out.println("aaaaaaaaaaaa");
        return list;
    }
	
	@RequestMapping(value = "/recieve", method = RequestMethod.POST) 
	public CreateBalanceInfo recieve( @Validated  CreateBalanceInfo createbalanceinfo ){
		System.out.println(createbalanceinfo);
		System.out.println("aaaaaaaaaaaa");
		return createbalanceinfo;
	}

}
