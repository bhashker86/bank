package com.mybankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mybankapi.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Long>{

}
