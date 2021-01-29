package com.mybankapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mybankapi.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{

	Optional<Account> findByActNumber(Long actNumber);

}
