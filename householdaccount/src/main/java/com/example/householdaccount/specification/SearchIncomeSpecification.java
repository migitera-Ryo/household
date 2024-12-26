package com.example.householdaccount.specification;

import java.util.Date;
import java.util.Optional;

import javax.persistence.criteria.CriteriaQuery;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.householdaccount.entity.Income;
import com.example.householdaccount.entity.SearchResultIncome;

@Component
public class SearchIncomeSpecification {
	public Specification<SearchResultIncome> buildFindAllSpecification(Optional<Date> incomeFromDate, Optional<Date> incomeToDate,  Optional<Integer> incomeFromAmount, 
			Optional<Integer> incomeToAmount, Optional<Integer> incomeType,
			Optional<String> incomeNote, Optional<Boolean> deleteFrag) {
        return Specification.where(join())
                .and(incomeFromDate.map(this::byIncomeFromDate).orElse(null))
                .and(incomeToDate.map(this::byIncomeToDate).orElse(null))
                .and(incomeFromAmount.map(this::byIncomeFromAmount).orElse(null))
                .and(incomeToAmount.map(this::byIncomeToAmount).orElse(null))
                .and(incomeType.map(this::byIncomeType).orElse(null))
                .and(incomeNote.map(this::byIncomeNote).orElse(null))
                .and(deleteFrag.map(this::byDeleteFrag).orElse(null));
    }

    private Specification<SearchResultIncome> join() {
        return (root, query, builder) -> {
//            root.fetch("students", JoinType.INNER);
            return null;
        };
    }

    private Specification<SearchResultIncome> byIncomeFromDate(Date incomeFromDate) {
        return (root, query, builder) -> {
            return builder.greaterThan(root.get("incomeDate"), incomeFromDate);
        };
    }
    
    private Specification<SearchResultIncome> byIncomeToDate(Date incomeToDate) {
        return (root, query, builder) -> {
            return builder.lessThan(root.get("incomeDate"), incomeToDate);
        };
    }
    
    private Specification<SearchResultIncome> byIncomeFromAmount(Integer incomeFromAmount) {
        return (root, query, builder) -> {
            return builder.greaterThan(root.get("amount"), incomeFromAmount);
        };
    }
    
    private Specification<SearchResultIncome> byIncomeToAmount(Integer incomeToAmount) {
        return (root, query, builder) -> {
            return builder.lessThan(root.get("amount"), incomeToAmount);
        };
    }
    
    private Specification<SearchResultIncome> byIncomeType(Integer incomeType) {
        return (root, query, builder) -> {
            return builder.equal(root.get("incomeType"), incomeType);
        };
    }
    
    private Specification<SearchResultIncome> byIncomeNote(String incomeNote) {
        return (root, query, builder) -> {
            return builder.like(root.get("note"), "%" + incomeNote + "%");
        };
    }
    
    private Specification<SearchResultIncome> byDeleteFrag(Boolean deleteFrag) {
        return (root, query, builder) -> {
            return builder.equal(root.get("deleteFrag"), deleteFrag);
            
        };
    }
    
}
