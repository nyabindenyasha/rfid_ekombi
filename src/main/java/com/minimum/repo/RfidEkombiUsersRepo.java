package com.minimum.repo;

import org.springframework.data.repository.CrudRepository;
import com.minimum.model.RfidEkombiUsers;

public interface RfidEkombiUsersRepo extends CrudRepository<RfidEkombiUsers, Integer>{
	
	RfidEkombiUsers findByUsername(String username);
	
	RfidEkombiUsers findByRfidUniqueId(String rfidUniqueId);
	
	RfidEkombiUsers findByAccountId(int accountId);
	
}


