package com.minimum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minimum.repo.RfidEkombiKombiFareDistanceRepo;
import com.minimum.model.RfidEkombiKombiFareDistance;

@Service
public class RfidEkombiKombiFareDistanceService {

	@Autowired
	private RfidEkombiKombiFareDistanceRepo rfidEkombiKombiFareDistanceRepository;

	public List<RfidEkombiKombiFareDistance> findAll() {
		return (List<RfidEkombiKombiFareDistance>) rfidEkombiKombiFareDistanceRepository.findAll();
	}

	public RfidEkombiKombiFareDistance findOne(int id) {
		return rfidEkombiKombiFareDistanceRepository.findOne(id);
	}

	public RfidEkombiKombiFareDistance saveR(RfidEkombiKombiFareDistance rfidEkombiKombiFareDistance) {
		return rfidEkombiKombiFareDistanceRepository.save(rfidEkombiKombiFareDistance);
	}

	public void save(RfidEkombiKombiFareDistance b) {
		rfidEkombiKombiFareDistanceRepository.save(b);
	}

	public void delete(int id) {
		rfidEkombiKombiFareDistanceRepository.delete(id);
	}

}
