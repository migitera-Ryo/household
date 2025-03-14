package com.example.householdaccount.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.householdaccount.entity.Income;
import com.example.householdaccount.entity.SearchResultIncome;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.JoinType;



@Component
public class IncomeSpecification {
	public Specification<Income> buildFindAllSpecification(Optional<Integer> incomeFromAmount, 
			Optional<Integer> incomeToAmount, Optional<Integer> incomeType,
			Optional<String> incomeNote) {
        return Specification.where(join())
//                .and(incomeFromDate.map(this::byIncomeFromDate).orElse(null))
//                .and(incomeToDate.map(this::byIncomeToDate).orElse(null))
                .and(incomeFromAmount.map(this::byIncomeFromAmount).orElse(null))
                .and(incomeToAmount.map(this::byIncomeToAmount).orElse(null))
                .and(incomeType.map(this::byIncomeType).orElse(null))
//                .and(expenditureItemName.map(this::byExpenditureItemName).orElse(null))
                .and(incomeNote.map(this::byIncomeNote).orElse(null));
    }

    private Specification<Income> join() {
        return (root, query, builder) -> {
//            root.fetch("students", JoinType.INNER);
            return null;
        };
    }

    private Specification<Income> byIncomeFromDate(String incomeFromDate) {
        return (root, query, builder) -> {
            return builder.greaterThanOrEqualTo(root.get("INCOME_DATE"), incomeFromDate);
        };
    }
    
    private Specification<Income> byIncomeToDate(String incomeToDate) {
        return (root, query, builder) -> {
            return builder.lessThanOrEqualTo(root.get("INCOME_DATE"), incomeToDate);
        };
    }
    
    private Specification<Income> byIncomeFromAmount(Integer incomeFromAmount) {
        return (root, query, builder) -> {
            return builder.greaterThanOrEqualTo(root.get("amount"), incomeFromAmount);
        };
    }
    
    private Specification<Income> byIncomeToAmount(Integer incomeToAmount) {
        return (root, query, builder) -> {
            return builder.lessThanOrEqualTo(root.get("amount"), incomeToAmount);
        };
    }
    
    private Specification<Income> byIncomeType(Integer incomeType) {
        return (root, query, builder) -> {
            return builder.equal(root.get("incomeType"), incomeType);
        };
    }
    
//    private Specification<SearchResultIncome> byExpenditureItemName(String expenditureItemName) {
//        return (root, query, builder) -> {
//            return builder.equal(root.get("EXPENDITURE_EXPENSE_ITEM_NAME"), expenditureItemName);
//        };
//    }
    
    private Specification<Income> byIncomeNote(String incomeNote) {
        return (root, query, builder) -> {
            return builder.like(root.get("note"), "%" + incomeNote + "%");
        };
    }


}
