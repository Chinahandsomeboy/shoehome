package com.springboot.shoehome.service;

import com.springboot.shoehome.domain.ItemLargeType;
import com.springboot.shoehome.repository.ItemLargeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by acer on 2018/7/28.
 */
@Service
public class ItemLargeTypeService {

    @Autowired private ItemLargeTypeRepository itemLargeTypeRepository;

    private ItemLargeTypeRepository getItemLargeTypeRepository(){
        return itemLargeTypeRepository;
    }

    public void addItemLargeType(ItemLargeType itemSmallType){
        getItemLargeTypeRepository().save(itemSmallType);
    }

    public ItemLargeType getItemLargeTypeByid(String id){
        return getItemLargeTypeRepository().getOne(id);
    }
}
