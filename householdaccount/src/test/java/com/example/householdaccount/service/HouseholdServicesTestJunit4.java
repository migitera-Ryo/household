package com.example.householdaccount.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.householdaccount.entity.SearchResultIncomeForEditAndDelete;
import com.example.householdaccount.form.IncomeHouseholdForm;
import com.example.householdaccount.repository.IncomeHouseholdRepository;
import com.example.householdaccount.repository.SearchIncomeHouseholdRepositoryForEdit;

@RunWith(SpringRunner.class)
@SpringBootTest
class HouseholdServicesTestJunit4 {
	
	@Autowired
	HouseholdServices householdServices;
	
	@Autowired
	private IncomeHouseholdRepository incomeHouseholdRepository;
	
	@Autowired
	private SearchIncomeHouseholdRepositoryForEdit searchIncomeHouseholdRepositoryForEdit;
	
	//@Test
	void testSample() {
		System.out.println("test Start");
		int result = householdServices.sample();
		System.out.println("result:"+result);
		
		assertEquals(5,result);
	}
	
	@Test
	void testPostCreateIncomeInfo_正常処理() {
		Calendar cal = Calendar.getInstance();
     
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdFormat.format(cal.getTime());
        Date date = java.sql.Date.valueOf(dateString);
        
        
		IncomeHouseholdForm incomeCommand = new IncomeHouseholdForm();
		incomeCommand.setAmount(100);
		incomeCommand.setIncomeType(1);
		incomeCommand.setIncomeDate(date);
		incomeCommand.setNote("");
		
		long dataListCount = incomeHouseholdRepository.count();
		
		
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
		
		String strYear = sdfYear.format(cal.getTime());
		String subStrYear = strYear.substring(strYear.length() - 2);
		String strMonth = sdfMonth.format(cal.getTime());
		
		String incomeCountNumber = String.format("%05d", dataListCount + 1);
		
		String incomeNumber = "I" + subStrYear + strMonth + incomeCountNumber;
		
		householdServices.postCreateIncomeInfo(incomeCommand);
		SearchResultIncomeForEditAndDelete searchIncomeInfo = searchIncomeHouseholdRepositoryForEdit.findIncomeByBalanceCode(incomeNumber);
		assertEquals(100,searchIncomeInfo.getAmount());
		assertEquals(1,searchIncomeInfo.getIncomeType());
		assertEquals(date,searchIncomeInfo.getIncomeDate());
		assertEquals("",searchIncomeInfo.getNote());
	}
	
	@Test
	void testPostCreateIncomeInfo_例外処理() {
		Calendar cal = Calendar.getInstance();
     
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdFormat.format(cal.getTime());
        Date date = java.sql.Date.valueOf(dateString);
        
        
		IncomeHouseholdForm incomeCommand = new IncomeHouseholdForm();
		incomeCommand.setAmount(null);
		incomeCommand.setIncomeType(1);
		incomeCommand.setIncomeDate(date);
		incomeCommand.setNote("");
		
		long dataListCount = incomeHouseholdRepository.count();
		
		
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
		
		String strYear = sdfYear.format(cal.getTime());
		String subStrYear = strYear.substring(strYear.length() - 2);
		String strMonth = sdfMonth.format(cal.getTime());
		
		String incomeCountNumber = String.format("%05d", dataListCount + 1);
		
		String incomeNumber = "I" + subStrYear + strMonth + incomeCountNumber;
		try {
			householdServices.postCreateIncomeInfo(incomeCommand);
			SearchResultIncomeForEditAndDelete searchIncomeInfo = searchIncomeHouseholdRepositoryForEdit.findIncomeByBalanceCode(incomeNumber);
			assertEquals(100,searchIncomeInfo.getAmount());
			assertEquals(1,searchIncomeInfo.getIncomeType());
			assertEquals(date,searchIncomeInfo.getIncomeDate());
			assertEquals("",searchIncomeInfo.getNote());
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("argument cannot be null."));
		      throw e;
		    }
	}

}
