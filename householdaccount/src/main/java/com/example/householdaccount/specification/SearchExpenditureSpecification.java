package com.example.householdaccount.specification;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.householdaccount.entity.SearchResultExpenditure;
import com.example.householdaccount.entity.SearchResultIncome;

@Component
public class SearchExpenditureSpecification {
	public Specification<SearchResultExpenditure> buildFindAllSpecification(Optional<Date> expenditureFromDate, Optional<Date> expenditureToDate,  Optional<Integer> expenditureFromAmount, 
			Optional<Integer> expenditureToAmount, Optional<String> expenditureItemName,
			Optional<String> expenditureNote, Optional<Boolean> deleteFrag) {
        return Specification.where(join())
                .and(expenditureFromDate.map(this::byExpenditureFromDate).orElse(null))
                .and(expenditureToDate.map(this::byExpenditureToDate).orElse(null))
                .and(expenditureFromAmount.map(this::byExpenditureFromAmount).orElse(null))
                .and(expenditureToAmount.map(this::byExpenditureToAmount).orElse(null))
                .and(expenditureItemName.map(this::byExpenditureItemName).orElse(null))
                .and(expenditureNote.map(this::byExpenditureNote).orElse(null))
                .and(deleteFrag.map(this::byDeleteFrag).orElse(null));
    }

    private Specification<SearchResultExpenditure> join() {
        return (root, query, builder) -> {
//            root.fetch("students", JoinType.INNER);
            return null;
        };
    }

    private Specification<SearchResultExpenditure> byExpenditureFromDate(Date expenditureFromDate) {
        return (root, query, builder) -> {
            return builder.greaterThanOrEqualTo(root.get("expenditureDate"), expenditureFromDate);
        };
    }
    
    private Specification<SearchResultExpenditure> byExpenditureToDate(Date expenditureToDate) {
        return (root, query, builder) -> {
            return builder.lessThanOrEqualTo(root.get("expenditureDate"), expenditureToDate);
        };
    }
    
    private Specification<SearchResultExpenditure> byExpenditureFromAmount(Integer expenditureFromAmount) {
        return (root, query, builder) -> {
            return builder.greaterThanOrEqualTo(root.get("amount"), expenditureFromAmount);
        };
    }
    
    private Specification<SearchResultExpenditure> byExpenditureToAmount(Integer expenditureToAmount) {
        return (root, query, builder) -> {
            return builder.lessThanOrEqualTo(root.get("amount"), expenditureToAmount);
        };
    }
    
    private Specification<SearchResultExpenditure> byExpenditureItemName(String expenditureItemName) {
        return (root, query, builder) -> {
            return builder.equal(root.get("expenditureExpenseItemName"), expenditureItemName);
        };
    }
    
    private Specification<SearchResultExpenditure> byExpenditureNote(String expenditureNote) {
        return (root, query, builder) -> {
            return builder.like(root.get("note"), "%" + expenditureNote + "%");
        };
    }
    
    private Specification<SearchResultExpenditure> byDeleteFrag(Boolean deleteFrag) {
        return (root, query, builder) -> {
            return builder.equal(root.get("deleteFrag"), deleteFrag);
        };
    }
}
