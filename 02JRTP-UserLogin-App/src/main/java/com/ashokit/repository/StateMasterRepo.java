package com.ashokit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ashokit.Entity.StateMasterEntity;

public interface StateMasterRepo extends JpaRepository <StateMasterEntity, Serializable> {
	
	List<StateMasterEntity> findBycountryId(Integer countryId);

}
