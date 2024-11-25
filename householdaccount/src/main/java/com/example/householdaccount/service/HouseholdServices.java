package com.example.householdaccount.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.householdaccount.entity.Expenditure;
import com.example.householdaccount.entity.ExpenditureItems;
import com.example.householdaccount.entity.Income;
import com.example.householdaccount.entity.Income.IncomeNoVO;
import com.example.householdaccount.form.ExpenditureHouseholdInfo;
import com.example.householdaccount.form.InputHouseholdInfo;
import com.example.householdaccount.repository.ExpenditureHouseholdRepository;
import com.example.householdaccount.repository.GetExpenditureItemsRepository;
import com.example.householdaccount.repository.HouseholdRepository;

@Service
@Transactional
public class HouseholdServices {
	@Autowired
	private HouseholdRepository householdRepository;
	
	@Autowired
	private ExpenditureHouseholdRepository expenditureHouseholdRepository;
	
	@Autowired
	private GetExpenditureItemsRepository getExpenditureItemsRepository;
	
	
	public Income postCreateIncomeInfo(InputHouseholdInfo inputhouseholdinfo) {
		long list = householdRepository.count();
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
		
		String strYear = sdfYear.format(cal.getTime());
		String subStrYear = strYear.substring(strYear.length() - 2);
		String strMonth = sdfMonth.format(cal.getTime());
		
		String incomeCountNumber = String.format("%05d", list + 1);
		
		String incomeNumber = "I" + subStrYear + strMonth + incomeCountNumber;
		
		Income income = new Income(incomeNumber, inputhouseholdinfo);
		

		
        return householdRepository.save(income);
    }
	
	
	public Expenditure postCreateExpenditureInfo(ExpenditureHouseholdInfo expenditureHouseholdInfo) {
		long list = expenditureHouseholdRepository.count();
		
		//System.out.println(getExpenditureItemsRepository.findById(expenditureHouseholdInfo.getExpenditureItemCode()));
		ExpenditureItems expenditreItem = getExpenditureItemsRepository.findByExpenditureExpenseItemCode(expenditureHouseholdInfo.getExpenditureItemCode());
		//expenditureHouseholdInfo.setExpenditureItemName(expenditreItem.getExpenditureExpenseItemName());
		
		
		/*
		 * List<ExpenditureItems> expenditureItems =
		 * getExpenditureItemsRepository.findAll(); for(int i = 0; i <
		 * expenditureItems.size(); i++) {
		 * System.out.println("898989898989898989898989898989");
		 * System.out.println(expenditureHouseholdInfo.getExpenditureItemName());
		 * if(expenditureItems.get(i).getExpenditureExpenseItemName().equals(
		 * expenditureHouseholdInfo.getExpenditureItemName())) {
		 * System.out.println("通ったよ");
		 * expenditureHouseholdInfo.setExpenditureItemCode(expenditureItems.get(i).
		 * getExpenditureExpenseItemCode()); } }
		 */
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
		
		String strYear = sdfYear.format(cal.getTime());
		String subStrYear = strYear.substring(strYear.length() - 2);
		String strMonth = sdfMonth.format(cal.getTime());
		
		String expenditureCountNumber = String.format("%05d", list);
		
		String expenditureNumber = "E" + subStrYear + strMonth + expenditureCountNumber;
		
		Expenditure expenditure = new Expenditure(expenditureNumber, expenditureHouseholdInfo);
		

		
        return expenditureHouseholdRepository.save(expenditure);
    }
	
	
	public List<ExpenditureItems> expenditureItemsInfo() {
		return getExpenditureItemsRepository.findAll();
    }

}
