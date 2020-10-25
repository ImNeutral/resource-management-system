package com.monstarlab.rms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monstarlab.rms.model.SystemCodes;

@Repository
public interface SystemCodesRepository  extends JpaRepository<SystemCodes, Long>{
	List<SystemCodes> findByCategory(String category);
}
