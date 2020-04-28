package com.minimum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minimum.repo.RfidEkombiPaymentsRepo;
import com.minimum.model.RfidEkombiPayments;


@Service
public class RfidEkombiPaymentsService {

	@Autowired
	private RfidEkombiPaymentsRepo rfidEkombiPaymentsRepository;

	public List<RfidEkombiPayments> findAll() {
		return (List<RfidEkombiPayments>) rfidEkombiPaymentsRepository.findAll();
	}

	public RfidEkombiPayments findOne(int id) {
		return rfidEkombiPaymentsRepository.findOne(id);
	}

	public RfidEkombiPayments saveR(RfidEkombiPayments rfidEkombiPayments) {
		return rfidEkombiPaymentsRepository.save(rfidEkombiPayments);
	}

	public void save(RfidEkombiPayments b) {
		rfidEkombiPaymentsRepository.save(b);
	}

	public void delete(int id) {
		rfidEkombiPaymentsRepository.delete(id);
	}

}
