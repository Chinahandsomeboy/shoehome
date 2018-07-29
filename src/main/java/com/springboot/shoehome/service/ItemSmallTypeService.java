package com.springboot.shoehome.service;

import com.springboot.shoehome.repository.ItemSmallTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zn
 * @date 2018/7/28
 */
@Service
public class ItemSmallTypeService {

    @Autowired private ItemSmallTypeRepository itemSmallTypeRepository;
}
