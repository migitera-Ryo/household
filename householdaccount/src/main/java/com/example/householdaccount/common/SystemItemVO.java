package com.example.householdaccount.common;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.jmolecules.ddd.annotation.ValueObject;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ValueObject
@Embeddable
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Data

@EnableJpaAuditing
public class SystemItemVO implements Serializable{
	/** 初期登録日時 */
	@Column(nullable = false)
	@CreatedDate
	private LocalDateTime initialCreateDateAndTime = LocalDateTime.now();

	/** 初期登録担当者 */
	@Column(nullable = false)
	//@CreatedBy
	private String initialCreateUserCode = "a";

	/** 最終更新日時 */
	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime lastUpdateDateAndTime = LocalDateTime.now();

	/** 最終更新担当者 */
	@Column(nullable = false)
	//@LastModifiedBy
	private String lastUpdateUserCode = "b";


}
