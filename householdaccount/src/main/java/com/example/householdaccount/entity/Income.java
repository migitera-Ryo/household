package com.example.householdaccount.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.householdaccount.common.SystemItemVO;
import com.example.householdaccount.form.InputHouseholdInfo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.jmolecules.ddd.annotation.ValueObject;
import org.jmolecules.ddd.types.Identifier;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "income")
public class Income{
	@Id
	private IncomeNoVO incomeNo;
	
	@Column(nullable = false)
	private int amount;
	
	@Column(nullable = false)
	private int incomeType;
	
	@Column(nullable = false)
	private Date incomeDate;
	
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
	
	public Income(String incomeNo, InputHouseholdInfo inputhouseholdinfo) {
		//SystemItemVO sytemitemvo = new SystemItemVO();
		
		this.incomeNo = IncomeNoVO.of(incomeNo);
		this.amount = inputhouseholdinfo.getAmount();
		this.incomeType = inputhouseholdinfo.getIncomeType();
		this.incomeDate = inputhouseholdinfo.getIncomeDate();
		this.note = inputhouseholdinfo.getNote();
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
	public static class IncomeNoVO implements Serializable , Identifier{

		/** 受発注ヘッダNo */
		@Column(nullable = false, length = 14)
		private final String incomeNo;

		@Override
		public String toString() {
			return this.incomeNo;
		}

	}
}
