package com.onlinemarket.Repository;

import org.springframework.data.repository.CrudRepository;

import com.onlinemarket.Entities.StoreProductHistory;

public interface historyRepository extends CrudRepository<StoreProductHistory,Integer> {

}