package com.springboot.shoehome.repository;

import com.springboot.shoehome.domain.ItemLargeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author acer
 * @date 2018/7/28
 */
@Repository
public interface ItemLargeTypeRepository extends JpaRepository<ItemLargeType, String>{
}
