package com.springboot.shoehome.repository;

import com.springboot.shoehome.domain.DepositOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author acer
 * @date 2018/7/28
 */
@Repository
public interface DepositOrderRepository extends JpaRepository<DepositOrder, String>{
}
