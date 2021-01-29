package com.mybankapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybankapi.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

	public Optional<Customer> findByCustomerNumber(Long cnumber);
	public Optional<Customer> getCustomerByAadharNumberOrPanNumber(String aadhar,String pan);
}
