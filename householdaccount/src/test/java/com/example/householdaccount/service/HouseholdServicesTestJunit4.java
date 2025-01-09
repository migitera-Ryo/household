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

import com.example.householdaccount.entity.Income;
import com.example.householdaccount.entity.Expenditure;
import com.example.householdaccount.form.ExpenditureHouseholdForm;
import com.example.householdaccount.entity.SearchResultIncomeForEditAndDelete;
import com.example.householdaccount.form.IncomeHouseholdForm;
import com.example.householdaccount.form.SearchResultBalanceFormForEditAndDelete;
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
	void 登録正常処理テスト() {
		String dateStr = "2025-01-08";
        Date date = java.sql.Date.valueOf(dateStr);
        
        //収入の登録単体テストケース
		IncomeHouseholdForm incomeCommand = new IncomeHouseholdForm();
		incomeCommand.setAmount(1000);
		incomeCommand.setIncomeType(1);
		incomeCommand.setIncomeDate(date);
		incomeCommand.setNote("あああ");
		
		Income income = householdServices.postCreateIncomeInfo(incomeCommand);
		
		assertEquals(1000,income.getAmount());
		assertEquals(1,income.getIncomeType());
		assertEquals(date,income.getIncomeDate());
		assertEquals("あああ",income.getNote());
		
		
		//収入の登録単体テストケース
		ExpenditureHouseholdForm expenditureCommand = new ExpenditureHouseholdForm();
		expenditureCommand.setAmount(1000);
		expenditureCommand.setExpenditureItemCode("EI001");
		expenditureCommand.setExpenditureDate(date);
		expenditureCommand.setNote("あああ");
		
		Expenditure expenditure = householdServices.postCreateExpenditureInfo(expenditureCommand);;
		
		assertEquals(1000,expenditure.getAmount());
		assertEquals("EI001",expenditure.getExpenditureExpenseItemCode());
		assertEquals(date,expenditure.getExpenditureDate());
		assertEquals("あああ",expenditure.getNote());
	}
	
	@Test
	void 登録例外処理テスト() {
		
		String dateStr = "2025-01-08";
        Date date = java.sql.Date.valueOf(dateStr);
        
        //登録情報がnullの場合
		IncomeHouseholdForm incomeCommand = new IncomeHouseholdForm();
		
		
		try {
			Income income = householdServices.postCreateIncomeInfo(incomeCommand);
		}catch(IllegalArgumentException ex){
			assertEquals("登録データがnullです",ex.getMessage());
		}
		
		
		//金額桁数が8桁以上
		incomeCommand.setAmount(1000000000);
		incomeCommand.setIncomeType(1);
		incomeCommand.setIncomeDate(date);
		incomeCommand.setNote("あああ");
		
		try {
			Income income = householdServices.postCreateIncomeInfo(incomeCommand);
		}catch(IllegalArgumentException ex){
			assertEquals("登録情報が不正です",ex.getMessage());
		}
	}
	
	@Test
	void 編集正常処理テスト() {
		SearchResultBalanceFormForEditAndDelete editCommand = new SearchResultBalanceFormForEditAndDelete();
		
		String dateStr = "2025-01-08";
        Date date = java.sql.Date.valueOf(dateStr);
        
        //収入の編集単体テストケース
        editCommand.setBalanceType("収入");
        editCommand.setBalanceCode("I250100003");
        editCommand.setAmount(1500);
        editCommand.setBalanceDate(date);
        editCommand.setIncomeType(1);
        editCommand.setExpenditureExpenseItemName(null);
        editCommand.setNote("あああ");
        editCommand.setVersion(0);
		
		Income income = householdServices.postEditIncomeInfo(editCommand);
		
		assertEquals("I250100003",String.valueOf(income.getIncomeNo()));
		assertEquals(1500,income.getAmount());
		assertEquals(date,income.getIncomeDate());
		assertEquals(1,income.getIncomeType());
		assertEquals("あああ",income.getNote());
		assertEquals(1,income.getVersion());
		
		//支出の編集単体テストケース
        editCommand.setBalanceType("支出");
        editCommand.setBalanceCode("E250100003");
        editCommand.setAmount(1000);
        editCommand.setBalanceDate(date);
        editCommand.setIncomeType(null);
        editCommand.setExpenditureExpenseItemName("日用品");
        editCommand.setNote("あああ");
        editCommand.setVersion(0);
		
        Expenditure expenditure = householdServices.postEditExpenditureInfo(editCommand);
		
		assertEquals("E250100003",String.valueOf(expenditure.getExpenditureNo()));
		assertEquals(1000,expenditure.getAmount());
		assertEquals(date,expenditure.getExpenditureDate());
		assertEquals("日用品",expenditure.getExpenditureExpenseItemName());
		assertEquals("あああ",expenditure.getNote());
		assertEquals(1,expenditure.getVersion());
	}

}
