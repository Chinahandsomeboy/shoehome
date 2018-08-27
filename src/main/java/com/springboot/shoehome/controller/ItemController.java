package com.springboot.shoehome.controller;

import com.springboot.shoehome.domain.Item;
import com.springboot.shoehome.service.ItemLargeTypeService;
import com.springboot.shoehome.service.ItemService;
import com.springboot.shoehome.service.ItemSmallTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 *
 * @author zn
 * @date 2018/7/28
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired private ItemService itemService;
    @Autowired private ItemLargeTypeService itemLargeTypeService;
    @Autowired private ItemSmallTypeService itemSmallTypeService;

    @GetMapping("/{id}")
    public String addItem (@PathVariable("id") String id){
        Item item = new Item();
        item.setName("修衣服");
        item.setPrice(10);
        item.setCode(id);
        item.setCreateDate(new Date());
        item.setItemLargeType(itemLargeTypeService.getItemLargeTypeByid("4028fe5c6578ef16016578f0dfe40007"));
        item.setItemSmallType(itemSmallTypeService.getItemSmallTypeByid("4028fe5c6578ef16016578f0add70005"));
        itemService.addItem(item);
        return id;
    }


    @GetMapping("/get")
    public List getItem(){
        return itemService.getItem();
    }
}
