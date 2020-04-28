package com.minimum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minimum.repo.RfidEkombiAccountRepo;
import com.minimum.model.RfidEkombiAccount;


@Service
public class RfidEkombiAccountService {

	@Autowired
	private RfidEkombiAccountRepo rfidEkombiAccountRepository;

	public List<RfidEkombiAccount> findAll() {
		return (List<RfidEkombiAccount>) rfidEkombiAccountRepository.findAll();
	}

	public RfidEkombiAccount findOne(int id) {
		return rfidEkombiAccountRepository.findOne(id);
	}

	public RfidEkombiAccount saveR(RfidEkombiAccount rfidEkombiAccount) {
		return rfidEkombiAccountRepository.save(rfidEkombiAccount);
	}

	public void save(RfidEkombiAccount b) {
		rfidEkombiAccountRepository.save(b);
	}

	public void delete(int id) {
		rfidEkombiAccountRepository.delete(id);
	}

}
