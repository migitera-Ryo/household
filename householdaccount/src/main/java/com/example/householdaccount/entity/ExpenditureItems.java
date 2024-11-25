package com.example.householdaccount.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jmolecules.ddd.annotation.ValueObject;
import org.jmolecules.ddd.types.Identifier;

import com.example.householdaccount.common.SystemItemVO;
import com.example.householdaccount.entity.Income.IncomeNoVO;
import com.example.householdaccount.form.ExpenditureItemInfo;
import com.example.householdaccount.form.InputHouseholdInfo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "expenditureexpenseitem")
public class ExpenditureItems {
	@Id
	private ExpenditureExpenseItemCodeVO expenditureExpenseItemCode;
	
	@Column(nullable = false)
	private String expenditureExpenseItemName;
	
	@Column(nullable = false)
	private String expenditureExpenseItemNameKana;
	
	public ExpenditureItems(ExpenditureItemInfo expenditureiteminfo) {
		this.expenditureExpenseItemCode = ExpenditureExpenseItemCodeVO.of(expenditureiteminfo.getExpenditureExpenseItemCode());
		this.expenditureExpenseItemName = expenditureiteminfo.getExpenditureExpenseItemName();
		this.expenditureExpenseItemNameKana = expenditureiteminfo.getExpenditureExpenseItemNameKana();
	}
	
	
	@ValueObject
	@Embeddable
	@Value
	@AllArgsConstructor(staticName = "of")
	@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
	@JsonSerialize(using = ToStringSerializer.class)
	public static class ExpenditureExpenseItemCodeVO implements Serializable , Identifier{

		/** 受発注ヘッダNo */
		@Column(nullable = false, length = 5)
		private final String expenditureExpenseItemCode;

		@Override
		public String toString() {
			return this.expenditureExpenseItemCode;
		}

	}

}
