package com.mybankapi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="transaction")
public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TXN_ID")
	private Long id;
	private Long actNumber;
	private String txnType;
	private Double txnAmount;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd-MM-yyyy", timezone="Asia/Kolkata")
	private Date txnDate;
	

}
