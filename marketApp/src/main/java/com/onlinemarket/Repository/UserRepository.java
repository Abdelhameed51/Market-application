package com.onlinemarket.Repository;

import org.springframework.data.repository.CrudRepository;

import com.onlinemarket.Entities.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
}
