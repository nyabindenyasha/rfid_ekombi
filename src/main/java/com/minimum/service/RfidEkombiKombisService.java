package com.minimum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minimum.repo.RfidEkombiKombisRepo;
import com.minimum.model.RfidEkombiKombis;


@Service
public class RfidEkombiKombisService {

	@Autowired
	private RfidEkombiKombisRepo rfidEkombiKombisRepository;

	public List<RfidEkombiKombis> findAll() {
		return (List<RfidEkombiKombis>) rfidEkombiKombisRepository.findAll();
	}

	public RfidEkombiKombis findOne(int id) {
		return rfidEkombiKombisRepository.findOne(id);
	}

	public RfidEkombiKombis saveR(RfidEkombiKombis rfidEkombiKombis) {
		return rfidEkombiKombisRepository.save(rfidEkombiKombis);
	}

	public void save(RfidEkombiKombis b) {
		rfidEkombiKombisRepository.save(b);
	}

	public void delete(int id) {
		rfidEkombiKombisRepository.delete(id);
	}

}
