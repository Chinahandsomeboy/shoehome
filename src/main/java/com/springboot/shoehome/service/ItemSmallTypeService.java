package com.springboot.shoehome.service;

import com.springboot.shoehome.domain.ItemSmallType;
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

    private ItemSmallTypeRepository getItemSmallTypeRepository(){
        return itemSmallTypeRepository;
    }

    public void addItemSmallType(ItemSmallType itemSmallType){
        getItemSmallTypeRepository().save(itemSmallType);
    }

    public ItemSmallType getItemSmallTypeByid(String id){
        return getItemSmallTypeRepository().getOne(id);
    }
}
