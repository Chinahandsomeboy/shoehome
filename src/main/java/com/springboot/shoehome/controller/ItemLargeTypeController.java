package com.springboot.shoehome.controller;

import com.springboot.shoehome.domain.ItemLargeType;
import com.springboot.shoehome.service.ItemLargeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 *
 * @author zn
 * @date 2018/7/28
 */
@RestController
@RequestMapping("/itemlarge")
public class ItemLargeTypeController {

    @Autowired private ItemLargeTypeService itemLargeTypeService;

    @GetMapping("/{name}")
    public String addItemLarge(@PathVariable("name") String name){
        ItemLargeType itemLargeType = new ItemLargeType();
        itemLargeType.setName(name);
        itemLargeType.setCode("123");
        itemLargeType.setCreateDate(new Date());
        itemLargeTypeService.addItemLargeType(itemLargeType);
        return name;
    }



}
