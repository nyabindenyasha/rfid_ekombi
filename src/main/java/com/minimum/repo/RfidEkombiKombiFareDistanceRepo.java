package com.minimum.repo;

import org.springframework.data.repository.CrudRepository;
import com.minimum.model.RfidEkombiKombiFareDistance;

public interface RfidEkombiKombiFareDistanceRepo extends CrudRepository<RfidEkombiKombiFareDistance, Integer>{
	
	RfidEkombiKombiFareDistance findByDistance(double distance);

}


