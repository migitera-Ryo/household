package com.example.householdaccount.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.example.householdaccount.entity.Income.IncomeNoVO;
import com.example.householdaccount.entity.SearchResultExpenditure;
import com.example.householdaccount.entity.SearchResultIncome;
import com.example.householdaccount.entity.SearchResultIncomeForEdit;
import com.example.householdaccount.entity.SearchResultExpenditureForEdit;
import com.example.householdaccount.form.DetailSearchForm;
import com.example.householdaccount.form.ExpenditureHouseholdForm;
import com.example.householdaccount.form.IncomeHouseholdForm;
import com.example.householdaccount.form.SearchResultBalanceForm;
import com.example.householdaccount.form.SearchResultBalanceFormForEditAndDelete;
import com.example.householdaccount.form.SendFrontBalanceResultFormForEdit;
import com.example.householdaccount.service.HouseholdServices;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class HouseholeController {
	@Autowired
	HouseholdServices householdServices;
	
	//収入データを検索して、結果を返す
	@GetMapping("/searchIncome")
    public List<SearchResultIncome> getSearchIncome(@RequestParam("ID") String balanceCode) {
		System.out.println("0000000000000000000000000000000000000");
		System.out.println(balanceCode);
		List<SearchResultIncome> searchBalanceResult = householdServices.getSearchIncomeInfoList(balanceCode);
        // ユーザーリストを返す
		System.out.println(searchBalanceResult);
        return searchBalanceResult;
    }
	
	//支出データを検索して、結果を返す
	@GetMapping("/searchExpenditure")
    public List<SearchResultExpenditure> getSearchExpenditure(@RequestParam("ID") String balanceCode) {
		System.out.println("0000000000000000000000000000000000000");
		System.out.println(balanceCode);
		List<SearchResultExpenditure> searchBalanceResult = householdServices.getSearchExpenditureInfoList(balanceCode);
        // ユーザーリストを返す
		System.out.println(searchBalanceResult);
        return searchBalanceResult;
    }
	
	//収入データと支出データを同時に検索して、同時に結果を返す
	@GetMapping("/searchBalance")
    public List<SearchResultBalanceForm> getSearchBalance(@RequestParam("ID") String balanceCode) {
		System.out.println("0000000000000000000000000000000000000");
		System.out.println(balanceCode);
		List<SearchResultIncome> searchIncomeResult = householdServices.getSearchIncomeInfoList(balanceCode);
		List<SearchResultExpenditure> searchExpenditureResult = householdServices.getSearchExpenditureInfoList(balanceCode);
		
		List<SearchResultBalanceForm> searchBalanceResult = new ArrayList<SearchResultBalanceForm>();
		
		
		for(int i = 0; i < searchIncomeResult.size(); i++) {
			SearchResultBalanceForm searchResultBalanceForm = new SearchResultBalanceForm();
			String code = String.valueOf(searchIncomeResult.get(i).getIncomeNo());
			String date = new SimpleDateFormat("yyyy-MM-dd").format(searchIncomeResult.get(i).getIncomeDate());
			
			searchResultBalanceForm.setBalanceType("収入");
			searchResultBalanceForm.setBalanceCode(code);
			searchResultBalanceForm.setAmount(searchIncomeResult.get(i).getAmount());
			searchResultBalanceForm.setBalanceDate(date);
			searchResultBalanceForm.setIncomeType(searchIncomeResult.get(i).getIncomeType());
			searchResultBalanceForm.setNote(searchIncomeResult.get(i).getNote());
			searchBalanceResult.add(searchResultBalanceForm);
		}
		
		for(int i = 0; i < searchExpenditureResult.size(); i++) {
			SearchResultBalanceForm searchResultBalanceForm = new SearchResultBalanceForm();
			String code = String.valueOf(searchExpenditureResult.get(i).getExpenditureNo());
			String date = new SimpleDateFormat("yyyy-MM-dd").format(searchExpenditureResult.get(i).getExpenditureDate());
			
			searchResultBalanceForm.setBalanceType("支出");
			searchResultBalanceForm.setBalanceCode(code);
			searchResultBalanceForm.setAmount(searchExpenditureResult.get(i).getAmount());
			searchResultBalanceForm.setBalanceDate(date);
			searchResultBalanceForm.setExpenditureExpenseItemName(searchExpenditureResult.get(i).getExpenditureExpenseItemName());
			searchResultBalanceForm.setNote(searchExpenditureResult.get(i).getNote());
			searchBalanceResult.add(searchResultBalanceForm);
		}
        // ユーザーリストを返す
		System.out.println("ppppppppppppppppppppppppppppppppppppppppp");
		System.out.println(searchBalanceResult);
        return searchBalanceResult;
    }
	
	
	//sisaku
	//収入データと支出データを同時に検索して、同時に結果を返す
		@GetMapping("/sisaku")
	    public SendFrontBalanceResultFormForEdit getSearchBalanceForEdit(@RequestParam("ID") String balanceCode) {
			System.out.println("99999999999999999999999999999");
			System.out.println(balanceCode);
			
			SearchResultIncomeForEdit searchResultIncomeForEdit = householdServices.getSearchIncomeInfo(balanceCode);
			SearchResultExpenditureForEdit searchResultExpenditureForEdit = householdServices.getSearchExpenditureInfo(balanceCode);
			
			SendFrontBalanceResultFormForEdit searchResultBalanceFormForEdit = new SendFrontBalanceResultFormForEdit();
			
			if(searchResultIncomeForEdit != null) {
				String code = String.valueOf(searchResultIncomeForEdit.getIncomeNo());
	            String date = new SimpleDateFormat("yyyy-MM-dd").format(searchResultIncomeForEdit.getIncomeDate());
				
				searchResultBalanceFormForEdit.setBalanceType("収入");
				searchResultBalanceFormForEdit.setBalanceCode(code);
				searchResultBalanceFormForEdit.setAmount(searchResultIncomeForEdit.getAmount());
				searchResultBalanceFormForEdit.setBalanceDate(date);
				searchResultBalanceFormForEdit.setIncomeType(searchResultIncomeForEdit.getIncomeType());
				searchResultBalanceFormForEdit.setNote(searchResultIncomeForEdit.getNote());
				searchResultBalanceFormForEdit.setVersion(searchResultIncomeForEdit.getVersion());
			}else {
				String code = String.valueOf(searchResultExpenditureForEdit.getExpenditureNo());
				String date = new SimpleDateFormat("yyyy-MM-dd").format(searchResultExpenditureForEdit.getExpenditureDate());
				
				searchResultBalanceFormForEdit.setBalanceType("支出");
				searchResultBalanceFormForEdit.setBalanceCode(code);
				searchResultBalanceFormForEdit.setAmount(searchResultExpenditureForEdit.getAmount());
				searchResultBalanceFormForEdit.setBalanceDate(date);
				searchResultBalanceFormForEdit.setExpenditureExpenseItemName(searchResultExpenditureForEdit.getExpenditureExpenseItemName());
				searchResultBalanceFormForEdit.setNote(searchResultExpenditureForEdit.getNote());
				searchResultBalanceFormForEdit.setVersion(searchResultExpenditureForEdit.getVersion());
			}
	
	        // ユーザーリストを返す
			System.out.println("jjjjjjjjjjjjjjjjjjjj");
			System.out.println(searchResultBalanceFormForEdit);
	        return searchResultBalanceFormForEdit;
	    }
		
	
	//収入データを詳細検索して結果を返す
	@GetMapping("/detailSearchIncome")
    public List<SearchResultBalanceForm> getDetailSearchIncome(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
    		@RequestParam("fromAmount") Optional<Integer> fromAmount,@RequestParam("toAmount") Optional<Integer> toAmount,
    		@RequestParam("incomeType") Optional<Integer> incomeType,@RequestParam("expenditureItemName") Optional<String> expenditureItemName,
    		@RequestParam("note") Optional<String> note) {
		
		Optional<Date> incomeFromDate;
		Optional<Date> incomeToDate;
		Optional<Boolean> deleteFlag = Optional.ofNullable(false);
		
		if(fromDate.isEmpty()) {
			incomeFromDate = Optional.empty();
		}else {
			Date sqlDate = java.sql.Date.valueOf(fromDate);
			incomeFromDate = Optional.ofNullable(sqlDate);
		}
		
		if(toDate.isEmpty()) {
			incomeToDate = Optional.empty();
		}else {
			Date sqlDate = java.sql.Date.valueOf(toDate);
			incomeToDate = Optional.ofNullable(sqlDate);
		}
		
		System.out.println("0000000000000000000000000000000000000");
		System.out.println(fromDate);
		System.out.println(toDate);
		System.out.println(incomeFromDate);
		System.out.println(incomeToDate);
		System.out.println(fromAmount);
		System.out.println(toAmount);
		System.out.println(incomeType);
		System.out.println(expenditureItemName);
		System.out.println(note);
		
		List<SearchResultIncome> searchIncomeResult = householdServices.getDetailSearchIncomeInfo(incomeFromDate,incomeToDate,
				fromAmount,toAmount,incomeType,expenditureItemName,note,deleteFlag);
		
		List<SearchResultBalanceForm> searchBalanceResult = new ArrayList<SearchResultBalanceForm>();
		
		for(int i = 0; i < searchIncomeResult.size(); i++) {
			SearchResultBalanceForm searchResultBalanceForm = new SearchResultBalanceForm();
			String code = String.valueOf(searchIncomeResult.get(i).getIncomeNo());
			String date = new SimpleDateFormat("yyyy-MM-dd").format(searchIncomeResult.get(i).getIncomeDate());
			
			searchResultBalanceForm.setBalanceType("収入");
			searchResultBalanceForm.setBalanceCode(code);
			searchResultBalanceForm.setAmount(searchIncomeResult.get(i).getAmount());
			searchResultBalanceForm.setBalanceDate(date);
			searchResultBalanceForm.setIncomeType(searchIncomeResult.get(i).getIncomeType());
			searchResultBalanceForm.setNote(searchIncomeResult.get(i).getNote());
			searchBalanceResult.add(searchResultBalanceForm);
		}
        // ユーザーリストを返す
		System.out.println("888888888888888888888888888888888888888");
		System.out.println(searchBalanceResult);
        return searchBalanceResult;
    }
	
	//支出データを詳細検索して結果を返す
		@GetMapping("/detailSearchExpenditure")
	    public List<SearchResultBalanceForm> getDetailSearchExpenditure(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
	    		@RequestParam("fromAmount") Optional<Integer> fromAmount,@RequestParam("toAmount") Optional<Integer> toAmount,
	    		@RequestParam("incomeType") Optional<Integer> incomeType,@RequestParam("expenditureItemName") String expenditureItemName,
	    		@RequestParam("note") Optional<String> note) {
			
			Optional<Date> expenditureFromDate;
			Optional<Date> expenditureToDate;
			Optional<String> optionalExpenditureItemName;
			
			
			//string型をoptional<>に変換
			if(fromDate.isEmpty()) {
				expenditureFromDate = Optional.empty();
			}else {
				Date sqlDate = java.sql.Date.valueOf(fromDate);
				expenditureFromDate = Optional.ofNullable(sqlDate);
			}
			
			if(toDate.isEmpty()) {
				expenditureToDate = Optional.empty();
			}else {
				Date sqlDate = java.sql.Date.valueOf(toDate);
				expenditureToDate = Optional.ofNullable(sqlDate);
			}
			
			if(expenditureItemName.isEmpty()) {
				optionalExpenditureItemName = Optional.empty();
			}else {
				optionalExpenditureItemName = Optional.ofNullable(expenditureItemName);
			}
			
			//詳細検索
			List<SearchResultExpenditure> searchExpenditureResult = householdServices.getDetailSearchExpenditureInfo(expenditureFromDate,expenditureToDate,
					fromAmount,toAmount,incomeType,optionalExpenditureItemName,note);
			
			//詳細検索結果を格納するリスト
			List<SearchResultBalanceForm> searchBalanceResult = new ArrayList<SearchResultBalanceForm>();
			
			//詳細検索結果を格納
			for(int i = 0; i < searchExpenditureResult.size(); i++) {
				SearchResultBalanceForm searchResultBalanceForm = new SearchResultBalanceForm();
				String code = String.valueOf(searchExpenditureResult.get(i).getExpenditureNo());
				String date = new SimpleDateFormat("yyyy-MM-dd").format(searchExpenditureResult.get(i).getExpenditureDate());
				
				searchResultBalanceForm.setBalanceType("支出");
				searchResultBalanceForm.setBalanceCode(code);
				searchResultBalanceForm.setAmount(searchExpenditureResult.get(i).getAmount());
				searchResultBalanceForm.setBalanceDate(date);
				searchResultBalanceForm.setExpenditureExpenseItemName(searchExpenditureResult.get(i).getExpenditureExpenseItemName());
				searchResultBalanceForm.setNote(searchExpenditureResult.get(i).getNote());
				searchBalanceResult.add(searchResultBalanceForm);
			}
			
	        // ユーザーリストを返す
			System.out.println("詳細検索の結果です");
			System.out.println(searchBalanceResult);
	        return searchBalanceResult;
	    }
		
		//収支データを詳細検索して結果を返す
		@GetMapping("/detailSearchBalance")
	    public List<SearchResultBalanceForm> getDetailSearchBalance(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
	    		@RequestParam("fromAmount") Optional<Integer> fromAmount,@RequestParam("toAmount") Optional<Integer> toAmount,
	    		@RequestParam("incomeType") Optional<Integer> incomeType,@RequestParam("expenditureItemName") String expenditureItemName,
	    		@RequestParam("note") Optional<String> note) {
			
			Optional<Date> incomeFromDate;
			Optional<Date> incomeToDate;
			
			Optional<Date> expenditureFromDate;
			Optional<Date> expenditureToDate;
			
			Optional<String> optionalExpenditureItemName;
			
			Optional<Boolean> deleteFlag = Optional.ofNullable(false);
			
			//string型をoptional<string>に変換
			if(fromDate.isEmpty()) {
				incomeFromDate = Optional.empty();
				expenditureFromDate = Optional.empty();
			}else {
				Date sqlDate = java.sql.Date.valueOf(fromDate);
				incomeFromDate = Optional.ofNullable(sqlDate);
				expenditureFromDate = Optional.ofNullable(sqlDate);
			}
			
			if(toDate.isEmpty()) {
				incomeToDate = Optional.empty();
				expenditureToDate = Optional.empty();
			}else {
				Date sqlDate = java.sql.Date.valueOf(toDate);
				incomeToDate = Optional.ofNullable(sqlDate);
				expenditureToDate = Optional.ofNullable(sqlDate);
			}
			
			if(expenditureItemName.isEmpty()) {
				optionalExpenditureItemName = Optional.empty();
			}else {
				optionalExpenditureItemName = Optional.ofNullable(expenditureItemName);
			}
			
			//詳細検索
			List<SearchResultIncome> searchIncomeResult = householdServices.getDetailSearchIncomeInfo(incomeFromDate,incomeToDate,
					fromAmount,toAmount,incomeType,optionalExpenditureItemName,note,deleteFlag);
			
			//詳細検索
			List<SearchResultExpenditure> searchExpenditureResult = householdServices.getDetailSearchExpenditureInfo(expenditureFromDate,expenditureToDate,
					fromAmount,toAmount,incomeType,optionalExpenditureItemName,note);
			
			
			//詳細検索結果を格納するリスト
			List<SearchResultBalanceForm> searchBalanceResult = new ArrayList<SearchResultBalanceForm>();
			
			//詳細検索結果を格納(収入)
			for(int i = 0; i < searchIncomeResult.size(); i++) {
				SearchResultBalanceForm searchResultBalanceForm = new SearchResultBalanceForm();
				String code = String.valueOf(searchIncomeResult.get(i).getIncomeNo());
				String date = new SimpleDateFormat("yyyy-MM-dd").format(searchIncomeResult.get(i).getIncomeDate());
				
				searchResultBalanceForm.setBalanceType("収入");
				searchResultBalanceForm.setBalanceCode(code);
				searchResultBalanceForm.setAmount(searchIncomeResult.get(i).getAmount());
				searchResultBalanceForm.setBalanceDate(date);
				searchResultBalanceForm.setIncomeType(searchIncomeResult.get(i).getIncomeType());
				searchResultBalanceForm.setNote(searchIncomeResult.get(i).getNote());
				searchBalanceResult.add(searchResultBalanceForm);
			}
			
			//詳細検索結果を格納(支出)
			for(int i = 0; i < searchExpenditureResult.size(); i++) {
				SearchResultBalanceForm searchResultBalanceForm = new SearchResultBalanceForm();
				String code = String.valueOf(searchExpenditureResult.get(i).getExpenditureNo());
				String date = new SimpleDateFormat("yyyy-MM-dd").format(searchExpenditureResult.get(i).getExpenditureDate());
				
				searchResultBalanceForm.setBalanceType("支出");
				searchResultBalanceForm.setBalanceCode(code);
				searchResultBalanceForm.setAmount(searchExpenditureResult.get(i).getAmount());
				searchResultBalanceForm.setBalanceDate(date);
				searchResultBalanceForm.setExpenditureExpenseItemName(searchExpenditureResult.get(i).getExpenditureExpenseItemName());
				searchResultBalanceForm.setNote(searchExpenditureResult.get(i).getNote());
				searchBalanceResult.add(searchResultBalanceForm);
			}
			
	        // ユーザーリストを返す
			System.out.println("詳細検索の結果です");
			System.out.println(searchBalanceResult);
	        return searchBalanceResult;
	    }
	
	@GetMapping("/expenseItems")
    public List<ExpenditureItem> getExpenseItem() {
		List<ExpenditureItem> expenditureItemList = householdServices.expenditureItemsInfo();
        // ユーザーリストを返す
		System.out.println("22222222222222222222222222222222222222222222222");
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
		
		householdServices.postCreateExpenditureInfo(expenditureCommand);
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiii");
		return "登録しました";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST) 
	public String eidt( @RequestBody @Validated SearchResultBalanceFormForEditAndDelete editCommand, BindingResult result){
		
		if(result.hasErrors()) {
		     return "編集できません";
		    }
		
		System.out.println(editCommand.getBalanceType());
		System.out.println("aaaaaaaaaaaa");
		
		if(editCommand.getBalanceType().equals("収入")) {
			System.out.println("収入の編集です");
			householdServices.postEditIncomeInfo(editCommand);
		}
		
		if(editCommand.getBalanceType().equals("支出")) {
			System.out.println("支出の編集です");
			householdServices.postEditExpenditureInfo(editCommand);
		}
		
		//
		
		return "編集しました、再度検索してください";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST) 
	public String delete( @RequestBody @Validated SearchResultBalanceFormForEditAndDelete deleteCommand, BindingResult result){
		
		if(result.hasErrors()) {
		     return "削除できません";
		    }
		
		System.out.println(deleteCommand.getBalanceType());
		System.out.println("aaaaaaaaaaaa");
		System.out.println(deleteCommand);
		
		if(deleteCommand.getBalanceType().equals("収入")) {
			System.out.println("収入の編集です");
			householdServices.postDeleteIncomeInfo(deleteCommand);
		}
		
		if(deleteCommand.getBalanceType().equals("支出")) {
			System.out.println("支出の編集です");
			householdServices.postDeleteExpenditureInfo(deleteCommand);
		}
		
		//
		
		return "削除しました";
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
