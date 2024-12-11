package com.example.householdaccount.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.householdaccount.entity.ExpenditureItem;
import com.example.householdaccount.entity.Income;
import com.example.householdaccount.entity.SearchResultExpenditure;
import com.example.householdaccount.entity.SearchResultIncome;
import com.example.householdaccount.form.DetailSearchForm;
import com.example.householdaccount.form.ExpenditureHouseholdForm;
import com.example.householdaccount.form.IncomeHouseholdForm;
import com.example.householdaccount.service.HouseholdServices;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class HouseholeController {
	@Autowired
	HouseholdServices householdServices;
	
	@GetMapping("/searchIncome")
    public List<SearchResultIncome> getSearchIncome(@RequestParam("ID") String balanceCode) {
		System.out.println("0000000000000000000000000000000000000");
		System.out.println(balanceCode);
		List<SearchResultIncome> searchBalanceResult = householdServices.getSearchIncomeInfo(balanceCode);
        // ユーザーリストを返す
		System.out.println(searchBalanceResult);
        return searchBalanceResult;
    }
	
	@GetMapping("/searchExpenditure")
    public List<SearchResultExpenditure> getSearchExpenditure(@RequestParam("ID") String balanceCode) {
		System.out.println("0000000000000000000000000000000000000");
		System.out.println(balanceCode);
		List<SearchResultExpenditure> searchBalanceResult = householdServices.getSearchExpenditureInfo(balanceCode);
        // ユーザーリストを返す
		System.out.println(searchBalanceResult);
        return searchBalanceResult;
    }
	
	@GetMapping("/detailsearch")
    public void getDetailSearchIncome(@RequestParam("fromDate") Optional<String> incomeFromDate,@RequestParam("toDate") Optional<String> incomeToDate,
    		@RequestParam("fromAmount") Optional<String> incomeFromAmount,@RequestParam("toAmount") Optional<String> incomeToAmount,
    		@RequestParam("incomeType") Optional<String> incomeType,@RequestParam("expenditureItemName") Optional<String> expenditureItemName,
    		@RequestParam("note") Optional<String> incomeNote) {
		System.out.println("0000000000000000000000000000000000000");
		System.out.println(incomeFromDate);
		System.out.println(incomeToDate);
		System.out.println(incomeFromAmount);
		System.out.println(incomeToAmount);
		System.out.println(incomeType);
		System.out.println(expenditureItemName);
		System.out.println(incomeNote);
		
		List<SearchResultIncome> searchBalanceResult = householdServices.getDetailSearchIncomeInfo(incomeFromDate,incomeToDate,
				incomeFromAmount,incomeToAmount,incomeType,expenditureItemName,incomeNote);
		
        // ユーザーリストを返す
		//System.out.println(searchBalanceResult);
//        return searchBalanceResult;
    }
	
	@GetMapping("/expenseItems")
    public List<ExpenditureItem> getExpenseItem() {
		List<ExpenditureItem> expenditureItemList = householdServices.expenditureItemsInfo();
        // ユーザーリストを返す
		System.out.println(expenditureItemList);
        return expenditureItemList;
    }
	
	@RequestMapping(value = "/income", method = RequestMethod.POST) 
	public String incomeCreate( @RequestBody @Validated IncomeHouseholdForm incomeCommmand,BindingResult result){
		
		if(result.hasErrors()) {
		     return "登録できません";
		    }
		System.out.println(incomeCommmand.getAmount());
		System.out.println(incomeCommmand.getIncomeDate());
		System.out.println(incomeCommmand.getIncomeType());
		System.out.println(incomeCommmand.getNote());
		
		
		householdServices.postCreateIncomeInfo(incomeCommmand);
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiii");
		return "登録しました";
	}
	
	@RequestMapping(value = "/expenditure", method = RequestMethod.POST) 
	public String expenditure( @RequestBody @Validated ExpenditureHouseholdForm expenditureCommand, BindingResult result){
		
		if(result.hasErrors()) {
		     return "登録できません";
		    }
		
		System.out.println(expenditureCommand.getExpenditureItemCode());
		System.out.println(expenditureCommand.getExpenditureDate());
		System.out.println("aaaaaaaaaaaa");
		
		//createincomeinfo.setIncomeNo("I240400001");
		//createincomeinfo.setInitialCreateDateAndTime(new Date());
		//createincomeinfo.setLastUpdateDateAndTime(new Date());
		//createincomeinfo.setInitialCreateUserCode("a");
		//createincomeinfo.setLastUpdateUserCode("a");
		//createincomeinfo.setVersion(0);
		
		//System.out.println(income.getInitialCreateDateAndTime());
		
		
		householdServices.postCreateExpenditureInfo(expenditureCommand);
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiii");
		return "登録しました";
	}
	
	
//	@ExceptionHandler(DataAccessException.class)
//    public String dataAccessExceptionHandler(DataAccessException e) {
//		System.out.println("lllllllllllllllllllllllllllllllllll");
//        return "'exclusive_error':システム管理者に連絡してください";
//    }

//    @ExceptionHandler(Exception.class)
//    public String exceptionHandler(Exception e) {
//    	
//    	System.out.println("ppppppppppppppppppppppppppppppppppp");
//        return "'exclusive_error':システム管理者に連絡してください";
//    }

}
