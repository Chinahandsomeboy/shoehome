package com.springboot.shoehome.controller;

import com.springboot.shoehome.domain.ItemSmallType;
import com.springboot.shoehome.service.ItemSmallTypeService;
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
@RequestMapping("/itemsmall")
public class ItemSmallTypeController {
    @Autowired private ItemSmallTypeService itemSmallTypeService;

    @GetMapping("/{name}")
    public String addItemLarge(@PathVariable("name") String name){
        ItemSmallType itemSmallType = new ItemSmallType();
        itemSmallType.setName(name);
        itemSmallType.setCode("123");
        itemSmallType.setCreateDate(new Date());
        itemSmallTypeService.addItemSmallType(itemSmallType);
        return name;
    }

}
