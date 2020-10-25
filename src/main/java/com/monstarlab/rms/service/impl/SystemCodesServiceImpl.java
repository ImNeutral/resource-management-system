package com.monstarlab.rms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monstarlab.rms.model.SystemCodes;
import com.monstarlab.rms.repository.SystemCodesRepository;
import com.monstarlab.rms.service.SystemCodesService;

@Service
@Transactional
public class SystemCodesServiceImpl implements SystemCodesService{

	@Autowired
	private SystemCodesRepository systemCodesRepository;
	
	@Override
	public List<SystemCodes> findAll() {
		return this.systemCodesRepository.findAll();
	}

	@Override
	public List<SystemCodes> findByCategory(String category) {
		return this.systemCodesRepository.findByCategory(category);
	}

	@Override
	public Optional<SystemCodes> findById(Long id) {
		return this.systemCodesRepository.findById(id);
	}

	@Override
	public SystemCodes save(SystemCodes systemCodes) {
		return this.systemCodesRepository.save(systemCodes);
	}

	@Override
	public void delete(Long id) {
		this.systemCodesRepository.deleteById(id);
	}	
}
