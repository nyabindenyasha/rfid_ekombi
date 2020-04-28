package com.minimum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minimum.repo.RfidEkombiKombiTrackerRepo;
import com.minimum.model.RfidEkombiKombiTracker;


@Service
public class RfidEkombiKombiTrackerService {

	@Autowired
	private RfidEkombiKombiTrackerRepo rfidEkombiKombiTrackerRepository;

	public List<RfidEkombiKombiTracker> findAll() {
		return (List<RfidEkombiKombiTracker>) rfidEkombiKombiTrackerRepository.findAll();
	}

	public RfidEkombiKombiTracker findOne(int id) {
		return rfidEkombiKombiTrackerRepository.findOne(id);
	}

	public RfidEkombiKombiTracker saveR(RfidEkombiKombiTracker rfidEkombiKombiTracker) {
		return rfidEkombiKombiTrackerRepository.save(rfidEkombiKombiTracker);
	}

	public void save(RfidEkombiKombiTracker b) {
		rfidEkombiKombiTrackerRepository.save(b);
	}

	public void delete(int id) {
		rfidEkombiKombiTrackerRepository.delete(id);
	}

}
