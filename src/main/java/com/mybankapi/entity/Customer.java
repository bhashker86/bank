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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="customer")
@Data
@NoArgsConstructor
public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
	@Column(unique=true)
	private Long customerNumber;
	private String email;
	private Long mobile;
	private String panNumber;
	private String aadharNumber;
	private String status;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Kolkata")
	private Date createDateTime;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Kolkata")
	private Date updateDateTime;
	
//	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	@JoinColumn(name="customer_id")
//	private List<Address> address;
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="account_id")
	private Account act;
		
}
