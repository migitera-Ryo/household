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
import com.example.householdaccount.form.ExpenditureHouseholdInfo;
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
@Table(name = "expenditure")
public class Expenditure {
	@Id
	private ExpenditureNoVO expenditureNo;
	
	@Column(nullable = false)
	private int amount;
	
	@Column(nullable = false)
	private String expenditureExpenseItemCode;
	
	@Column(nullable = false)
	private String expenditureExpenseItemName;
	
	@Column(nullable = false)
	private Date expenditureDate;
	
	@Column(nullable = true)
	private String note;
	
	@Column(nullable = false)
	private boolean deleteFrag;
	
	private final SystemItemVO systemItems = new SystemItemVO();
	
	/*
	 * @Column(nullable = false) private LocalDateTime initialCreateDateAndTime;
	 * 
	 * @Column(nullable = false) private LocalDateTime lastUpdateDateAndTime;
	 * 
	 * @Column(nullable = false) private String initialCreateUserCode;
	 * 
	 * @Column(nullable = false) private String lastUpdateUserCode;
	 */
	 
	@Column(nullable = false)
	private int version;
	
	public Expenditure(String expenditureNo, ExpenditureHouseholdInfo expenditureHouseholdInfo) {
		//SystemItemVO sytemitemvo = new SystemItemVO();
		
		this.expenditureNo = ExpenditureNoVO.of(expenditureNo);
		this.amount = expenditureHouseholdInfo.getAmount();
		this.expenditureExpenseItemCode = expenditureHouseholdInfo.getExpenditureItemCode();
		this.expenditureExpenseItemName = expenditureHouseholdInfo.getExpenditureItemName();
		this.expenditureDate = expenditureHouseholdInfo.getExpenditureDate();
		this.note = expenditureHouseholdInfo.getNote();
		this.deleteFrag = false;
		/*
		 * this.initialCreateDateAndTime = sytemitemvo.getInitialCreateDateAndTime();
		 * this.lastUpdateDateAndTime = sytemitemvo.getLastUpdateDateAndTime();
		 * this.initialCreateUserCode = sytemitemvo.getInitialCreateUserCode();
		 * this.lastUpdateUserCode = sytemitemvo.getLastUpdateUserCode();
		 */
		this.version = 0;
		
	}
	
	@ValueObject
	@Embeddable
	@Value
	@AllArgsConstructor(staticName = "of")
	@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
	@JsonSerialize(using = ToStringSerializer.class)
	public static class ExpenditureNoVO implements Serializable , Identifier{

		/** 受発注ヘッダNo */
		@Column(nullable = false, length = 10)
		private final String expenditureNo;

		@Override
		public String toString() {
			return this.expenditureNo;
		}

	}

}
