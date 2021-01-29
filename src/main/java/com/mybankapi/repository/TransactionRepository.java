package com.mybankapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mybankapi.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{

	List<Transaction> findByActNumber(Long ac);

	@Query(value="Select * from transaction where act_number=:ac "
			+ "and txn_date>=:startDate AND txn_date<=:endDate order by txn_date desc",nativeQuery=true)
	List<Transaction> getTransactionForRangeOfDate(@Param("ac")Long ac,
			@Param("startDate") Date startDate,@Param("endDate") Date endDate);

}
