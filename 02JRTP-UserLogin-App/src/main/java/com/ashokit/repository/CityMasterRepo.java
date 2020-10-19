package com.ashokit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.Entity.CityMasterEntity;

public interface CityMasterRepo extends JpaRepository<CityMasterEntity, Serializable> {

	List<CityMasterEntity> findBystateId(Integer stateId);

}
