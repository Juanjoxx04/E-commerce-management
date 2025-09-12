package com.E_commerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.E_commerce.model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>{

}
