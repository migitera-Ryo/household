package com.example.householdaccount.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.householdaccount.entity.ExpenditureItems;
import com.example.householdaccount.entity.Income;
import com.example.householdaccount.form.InputHouseholdInfo;
import com.example.householdaccount.repository.GetExpenditureItems;
import com.example.householdaccount.repository.HouseholdRepository;

@Service
@Transactional
public class HouseholdServices {
	@Autowired
	private HouseholdRepository householdRepository;
	
	@Autowired
	private GetExpenditureItems getExpenditureItems;
	
	public Income postCreateIncomeInfo(InputHouseholdInfo inputhouseholdinfo) {
		List<Income> list = householdRepository.findAll();
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
		
		String strYear = sdfYear.format(cal.getTime());
		String subStrYear = strYear.substring(strYear.length() - 2);
		String strMonth = sdfMonth.format(cal.getTime());
		
		String incomeCountNumber = String.format("%05d", list.size() + 1);
		
		String incomeNumber = "I" + subStrYear + strMonth + incomeCountNumber;
		
		Income income = new Income(incomeNumber, inputhouseholdinfo);
		

		
        return householdRepository.save(income);
    }
	
	
	public List<ExpenditureItems> expenditureItemsInfo() {
		return getExpenditureItems.findAll();
    }

}
