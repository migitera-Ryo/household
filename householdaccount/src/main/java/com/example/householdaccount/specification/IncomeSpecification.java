package com.example.householdaccount.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.householdaccount.entity.SearchResultIncome;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.JoinType;



@Component
public class IncomeSpecification {
	public Specification<SearchResultIncome> buildFindAllSpecification(Optional<String> incomeFromDate, 
			Optional<String> incomeToDate, Optional<String> incomeFromAmount, 
			Optional<String> incomeToAmount, Optional<String> incomeType,
			Optional<String> incomeNote) {
        return Specification.where(join())
                .and(incomeFromDate.map(this::byIncomeFromDate).orElse(null))
                .and(incomeToDate.map(this::byIncomeToDate).orElse(null))
                .and(incomeFromAmount.map(this::byIncomeFromAmount).orElse(null))
                .and(incomeToAmount.map(this::byIncomeToAmount).orElse(null))
                .and(incomeType.map(this::byIncomeType).orElse(null))
//                .and(expenditureItemName.map(this::byExpenditureItemName).orElse(null))
                .and(incomeNote.map(this::byIncomeNote).orElse(null));
    }

    private Specification<SearchResultIncome> join() {
        return (root, query, builder) -> {
//            root.fetch("students", JoinType.INNER);
            return null;
        };
    }

    private Specification<SearchResultIncome> byIncomeFromDate(String incomeFromDate) {
        return (root, query, builder) -> {
            return builder.greaterThan(root.get("INCOME_DATE"), incomeFromDate);
        };
    }
    
    private Specification<SearchResultIncome> byIncomeToDate(String incomeToDate) {
        return (root, query, builder) -> {
            return builder.lessThan(root.get("INCOME_DATE"), incomeToDate);
        };
    }
    
    private Specification<SearchResultIncome> byIncomeFromAmount(String incomeFromAmount) {
        return (root, query, builder) -> {
            return builder.greaterThan(root.get("AMOUNT"), incomeFromAmount);
        };
    }
    
    private Specification<SearchResultIncome> byIncomeToAmount(String incomeToAmount) {
        return (root, query, builder) -> {
            return builder.lessThan(root.get("AMOUNT"), incomeToAmount);
        };
    }
    
    private Specification<SearchResultIncome> byIncomeType(String incomeType) {
        return (root, query, builder) -> {
            return builder.equal(root.get("INCOME_TYPE"), incomeType);
        };
    }
    
//    private Specification<SearchResultIncome> byExpenditureItemName(String expenditureItemName) {
//        return (root, query, builder) -> {
//            return builder.equal(root.get("EXPENDITURE_EXPENSE_ITEM_NAME"), expenditureItemName);
//        };
//    }
    
    private Specification<SearchResultIncome> byIncomeNote(String incomeNote) {
        return (root, query, builder) -> {
            return builder.like(root.get("NOTE"), "%" + incomeNote + "%");
        };
    }


}
