package com.mybankapi.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="account")
public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private Long actNumber;
	private Double actBalance;
	private String actType;
	private String actStatus;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Kolkata")
	private Date createDateTime;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Kolkata")
	private Date updateDateTime;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="account_id")
	private List<Transaction> transList;
	
}
