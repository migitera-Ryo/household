package com.example.householdaccount.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.householdaccount.entity.Expenditure;
import com.example.householdaccount.entity.ExpenditureItem;
import com.example.householdaccount.entity.ExpenditureItem.ExpenditureExpenseItemCodeVO;
import com.example.householdaccount.entity.Income;
import com.example.householdaccount.entity.Income.IncomeNoVO;
import com.example.householdaccount.entity.SearchResultExpenditure;
import com.example.householdaccount.entity.SearchResultIncome;
import com.example.householdaccount.form.ExpenditureHouseholdForm;
import com.example.householdaccount.form.IncomeHouseholdForm;
import com.example.householdaccount.repository.ExpenditureHouseholdRepository;
import com.example.householdaccount.repository.GetExpenditureItemsRepository;
import com.example.householdaccount.repository.IncomeHouseholdRepository;
import com.example.householdaccount.repository.SearchExpenditureHouseholdRepository;
import com.example.householdaccount.repository.SearchIncomeHouseholdRepository;
import com.example.householdaccount.specification.IncomeSpecification;

@Service
@Transactional
public class HouseholdServices {
	@Autowired
	private IncomeHouseholdRepository incomeHouseholdRepository;
	
	@Autowired
	private ExpenditureHouseholdRepository expenditureHouseholdRepository;
	
	@Autowired
	private GetExpenditureItemsRepository getExpenditureItemsRepository;
	
	@Autowired
	private SearchIncomeHouseholdRepository searchIncomeHouseholdRepository;
	
	@Autowired
	private SearchExpenditureHouseholdRepository searchExpenditureHouseholdRepository;
	
	@Autowired
	private IncomeSpecification incomeSpecification;
	
	
	
	public List<SearchResultIncome> getSearchIncomeInfo(String balanceCode) {
		System.out.println(balanceCode);
		List<SearchResultIncome> searchIncomeInfoList = searchIncomeHouseholdRepository.findIncomeByBalanceCode(balanceCode);
		System.out.println(searchIncomeInfoList);
		
		return searchIncomeInfoList;
    }
	
	public List<SearchResultIncome> getDetailSearchIncomeInfo(Optional<String> incomeFromDate, 
			Optional<String> incomeToDate, Optional<String> incomeFromAmount, 
			Optional<String> incomeToAmount, Optional<String> incomeType,
			Optional<String> expenditureItemName, Optional<String> incomeNote) {
		//System.out.println(balanceCode);
		List<SearchResultIncome> searchIncomeInfoList = searchIncomeHouseholdRepository.detailFindIncome(incomeSpecification.buildFindAllSpecification(incomeFromDate,incomeToDate,incomeFromAmount,incomeToAmount,incomeType,incomeNote));
		System.out.println(searchIncomeInfoList);
		
		return searchIncomeInfoList;
    }
	
	public List<SearchResultExpenditure> getSearchExpenditureInfo(String balanceCode) {
		System.out.println(balanceCode);
		List<SearchResultExpenditure> searchExpenditureInfoList = searchExpenditureHouseholdRepository.findExpenditureByBalanceCode(balanceCode);
		System.out.println(searchExpenditureInfoList);
		
		return searchExpenditureInfoList;
    }
	
	public Income postCreateIncomeInfo(IncomeHouseholdForm incomeCommand) {
		long dataList = incomeHouseholdRepository.count();
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
		
		String strYear = sdfYear.format(cal.getTime());
		String subStrYear = strYear.substring(strYear.length() - 2);
		String strMonth = sdfMonth.format(cal.getTime());
		
		String incomeCountNumber = String.format("%05d", dataList + 1);
		
		String incomeNumber = "I" + subStrYear + strMonth + incomeCountNumber;
		
		Income income = new Income(incomeNumber, incomeCommand);
		

		
        return incomeHouseholdRepository.save(income);
    }
	
	
	public Expenditure postCreateExpenditureInfo(ExpenditureHouseholdForm expenditureCommand) {
		long dataList = expenditureHouseholdRepository.count();
		
		ExpenditureItem.ExpenditureExpenseItemCodeVO expenditureItemCode = ExpenditureItem.ExpenditureExpenseItemCodeVO.of(expenditureCommand.getExpenditureItemCode());
		
		
		ExpenditureItem expenditreItem = getExpenditureItemsRepository.findByExpenditureExpenseItemCode(expenditureItemCode);
		expenditureCommand.setExpenditureItemName(expenditreItem.getExpenditureExpenseItemName());
		
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
		
		String strYear = sdfYear.format(cal.getTime());
		String subStrYear = strYear.substring(strYear.length() - 2);
		String strMonth = sdfMonth.format(cal.getTime());
		
		String expenditureCountNumber = String.format("%05d", dataList + 1);
		
		String expenditureNumber = "E" + subStrYear + strMonth + expenditureCountNumber;
		
		Expenditure expenditure = new Expenditure(expenditureNumber, expenditureCommand);
		

		
        return expenditureHouseholdRepository.save(expenditure);
    }
	
	
	public List<ExpenditureItem> expenditureItemsInfo() {
		return getExpenditureItemsRepository.findAll();
    }

}
