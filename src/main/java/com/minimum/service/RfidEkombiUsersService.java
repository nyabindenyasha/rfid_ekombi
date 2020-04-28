package com.minimum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minimum.repo.RfidEkombiUsersRepo;
import com.minimum.model.RfidEkombiUsers;


@Service
public class RfidEkombiUsersService {

	@Autowired
	private RfidEkombiUsersRepo rfidEkombiUsersRepository;

	public List<RfidEkombiUsers> findAll() {
		return (List<RfidEkombiUsers>) rfidEkombiUsersRepository.findAll();
	}

	public RfidEkombiUsers findOne(int id) {
		return rfidEkombiUsersRepository.findOne(id);
	}
	
	public RfidEkombiUsers findByAccountId(int accountId) {
		return rfidEkombiUsersRepository.findByAccountId(accountId);
	}
	
	public RfidEkombiUsers findByEmail(String username) {
		return rfidEkombiUsersRepository.findByUsername(username);
	}
	
	public RfidEkombiUsers findByRfidUniqueId(String rfidUniqueId) {
		return rfidEkombiUsersRepository.findByRfidUniqueId(rfidUniqueId);
	}

	public RfidEkombiUsers saveR(RfidEkombiUsers rfidEkombiUsers) {
		return rfidEkombiUsersRepository.save(rfidEkombiUsers);
	}

	public void save(RfidEkombiUsers b) {
		rfidEkombiUsersRepository.save(b);
	}

	public void delete(int id) {
		rfidEkombiUsersRepository.delete(id);
	}

}
