package com.example.householdaccount.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
import com.example.householdaccount.entity.SearchResultExpenditureForEditAndDelete;
import com.example.householdaccount.entity.SearchResultIncome;
import com.example.householdaccount.entity.SearchResultIncomeForEditAndDelete;
import com.example.householdaccount.form.ExpenditureHouseholdForm;
import com.example.householdaccount.form.IncomeHouseholdForm;
import com.example.householdaccount.form.SearchResultBalanceForm;

import com.example.householdaccount.form.SearchResultBalanceFormForEditAndDelete;
import com.example.householdaccount.repository.ExpenditureHouseholdRepository;
import com.example.householdaccount.repository.GetExpenditureItemsRepository;
import com.example.householdaccount.repository.IncomeHouseholdRepository;
import com.example.householdaccount.repository.SearchExpenditureHouseholdRepository;
import com.example.householdaccount.repository.SearchExpenditureHouseholdRepositoryForEdit;
import com.example.householdaccount.repository.SearchIncomeHouseholdRepository;
import com.example.householdaccount.repository.SearchIncomeHouseholdRepositoryForEdit;
import com.example.householdaccount.specification.IncomeSpecification;
import com.example.householdaccount.specification.SearchExpenditureSpecification;
import com.example.householdaccount.specification.SearchIncomeSpecification;

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
	private SearchIncomeHouseholdRepositoryForEdit searchIncomeHouseholdRepositoryForEdit;
	
	@Autowired
	private SearchExpenditureHouseholdRepositoryForEdit searchExpenditureHouseholdRepositoryForEdit;
	
	@Autowired
	private SearchIncomeSpecification searchIncomeSpecification;
	
	@Autowired
	private SearchExpenditureSpecification searchExpenditureSpecification;
	
	
	public int sample() {
		return 5;
    }
	
	public List<SearchResultIncome> getSearchIncomeInfoList(String balanceCode) {
		System.out.println(balanceCode);
		List<SearchResultIncome> searchIncomeInfoList = searchIncomeHouseholdRepository.findIncomeByBalanceCode(balanceCode);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println(searchIncomeInfoList.size());
		
		
		
		return searchIncomeInfoList;
    }
	
	//試作
	public SearchResultIncomeForEditAndDelete getSearchIncomeInfo(String balanceCode) {
		System.out.println(balanceCode);
		SearchResultIncomeForEditAndDelete searchIncomeInfo = searchIncomeHouseholdRepositoryForEdit.findIncomeByBalanceCode(balanceCode);
		System.out.println(searchIncomeInfo);
		
		return searchIncomeInfo;
    }
	
	public List<SearchResultIncome> getDetailSearchIncomeInfo(Optional<Date> incomeFromDate, 
			Optional<Date> incomeToDate, Optional<Integer> incomeFromAmount, 
			Optional<Integer> incomeToAmount, Optional<Integer> incomeType,
			Optional<String> expenditureItemName, Optional<String> incomeNote,Optional<Boolean> deleteFlag) {
	
		List<SearchResultIncome> searchIncomeInfoList = searchIncomeHouseholdRepository.findAll(searchIncomeSpecification.buildFindAllSpecification(incomeFromDate,incomeToDate,incomeFromAmount,incomeToAmount,incomeType,incomeNote,deleteFlag));
		
		
		System.out.println("2222222222222222222222222222222222222222222");
		
		System.out.println(searchIncomeInfoList);
		
		return searchIncomeInfoList;
    }
	
	public List<SearchResultExpenditure> getDetailSearchExpenditureInfo(Optional<Date> expenditureFromDate, 
			Optional<Date> expenditureToDate, Optional<Integer> expenditureFromAmount, 
			Optional<Integer> expenditureToAmount, Optional<Integer> incomeType,
			Optional<String> expenditureItemName, Optional<String> expenditureNote, Optional<Boolean> deleteFlag) {
		
		List<SearchResultExpenditure> searchExpenditureInfoList = searchExpenditureHouseholdRepository.findAll(searchExpenditureSpecification.buildFindAllSpecification(expenditureFromDate,expenditureToDate,expenditureFromAmount,expenditureToAmount,expenditureItemName,expenditureNote,deleteFlag));
		
		
		return searchExpenditureInfoList;
    }
	
	
	public List<SearchResultExpenditure> getSearchExpenditureInfoList(String balanceCode) {
		System.out.println(balanceCode);
		List<SearchResultExpenditure> searchExpenditureInfoList = searchExpenditureHouseholdRepository.findExpenditureByBalanceCode(balanceCode);
		System.out.println(searchExpenditureInfoList);
		
		return searchExpenditureInfoList;
    }
	
	//試作
	public SearchResultExpenditureForEditAndDelete getSearchExpenditureInfo(String balanceCode) {
		System.out.println(balanceCode);
		SearchResultExpenditureForEditAndDelete searchExpenditureInfo = searchExpenditureHouseholdRepositoryForEdit.findExpenditureByBalanceCode(balanceCode);
		System.out.println(searchExpenditureInfo);
		
		return searchExpenditureInfo;
    }
	
	//収入の登録
	public Income postCreateIncomeInfo(IncomeHouseholdForm incomeCommand) {
		//
		if(incomeCommand.getAmount() == null || incomeCommand.getIncomeDate() == null ||incomeCommand.getIncomeType() == null) {
			throw new IllegalArgumentException("argument cannot be null.");
		}
		
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
	
	//支出の登録
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
	
	//収入の編集
	public Income postEditIncomeInfo(SearchResultBalanceFormForEditAndDelete editCommand) {
		
		IncomeHouseholdForm incomeHouseholdForm = new IncomeHouseholdForm();
		incomeHouseholdForm.setAmount(editCommand.getAmount());
		incomeHouseholdForm.setIncomeType(editCommand.getIncomeType());
		incomeHouseholdForm.setIncomeDate(editCommand.getBalanceDate());
		incomeHouseholdForm.setNote(editCommand.getNote());
		
		int version = editCommand.getVersion();
		
		Income income = new Income(editCommand.getBalanceCode(), incomeHouseholdForm);
		
		income.setVersion(version + 1);
		
        return incomeHouseholdRepository.save(income);
    }
	
	//支出の編集
	public Expenditure postEditExpenditureInfo(SearchResultBalanceFormForEditAndDelete editCommand) {
		
		ExpenditureItem expenditureItem = getExpenditureItemsRepository.findByExpenditureExpenseItemName(editCommand.getExpenditureExpenseItemName());
		String expenditureExpenseItemCode= String.valueOf(expenditureItem.getExpenditureExpenseItemCode());

		ExpenditureHouseholdForm expenditureHouseholdForm = new ExpenditureHouseholdForm();
		expenditureHouseholdForm.setAmount(editCommand.getAmount());
		expenditureHouseholdForm.setExpenditureDate(editCommand.getBalanceDate());
		expenditureHouseholdForm.setExpenditureItemCode(expenditureExpenseItemCode);
		expenditureHouseholdForm.setExpenditureItemName(editCommand.getExpenditureExpenseItemName());
		expenditureHouseholdForm.setNote(editCommand.getNote());
		
		int version = editCommand.getVersion();
		
		Expenditure expenditure = new Expenditure(editCommand.getBalanceCode(),expenditureHouseholdForm);
		expenditure.setVersion(version + 1);

		
        return expenditureHouseholdRepository.save(expenditure);
    }
	
	//収入の削除
		public Income postDeleteIncomeInfo(SearchResultBalanceFormForEditAndDelete deleteCommand) {
			
			IncomeHouseholdForm incomeHouseholdForm = new IncomeHouseholdForm();
			incomeHouseholdForm.setAmount(deleteCommand.getAmount());
			incomeHouseholdForm.setIncomeType(deleteCommand.getIncomeType());
			incomeHouseholdForm.setIncomeDate(deleteCommand.getBalanceDate());
			incomeHouseholdForm.setNote(deleteCommand.getNote());
			
			int version = deleteCommand.getVersion();
			
			Income income = new Income(deleteCommand.getBalanceCode(), incomeHouseholdForm);
			
			income.setVersion(version + 1);
			income.setDeleteFrag(true);
			
	        return incomeHouseholdRepository.save(income);
	    }
		
		//支出の削除
		public Expenditure postDeleteExpenditureInfo(SearchResultBalanceFormForEditAndDelete deleteCommand) {
			
			ExpenditureItem expenditureItem = getExpenditureItemsRepository.findByExpenditureExpenseItemName(deleteCommand.getExpenditureExpenseItemName());
			String expenditureExpenseItemCode= String.valueOf(expenditureItem.getExpenditureExpenseItemCode());

			ExpenditureHouseholdForm expenditureHouseholdForm = new ExpenditureHouseholdForm();
			expenditureHouseholdForm.setAmount(deleteCommand.getAmount());
			expenditureHouseholdForm.setExpenditureDate(deleteCommand.getBalanceDate());
			expenditureHouseholdForm.setExpenditureItemCode(expenditureExpenseItemCode);
			expenditureHouseholdForm.setExpenditureItemName(deleteCommand.getExpenditureExpenseItemName());
			expenditureHouseholdForm.setNote(deleteCommand.getNote());
			
			int version = deleteCommand.getVersion();
			
			Expenditure expenditure = new Expenditure(deleteCommand.getBalanceCode(),expenditureHouseholdForm);
			expenditure.setVersion(version + 1);
			expenditure.setDeleteFrag(true);

			
	        return expenditureHouseholdRepository.save(expenditure);
	    }
	
	
	public List<ExpenditureItem> expenditureItemsInfo() {
		return getExpenditureItemsRepository.findAll();
    }

}
