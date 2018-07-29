package com.springboot.shoehome.service;

import com.springboot.shoehome.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by acer on 2018/7/28.
 */
@Service
public class ItemService {

    @Autowired private ItemRepository itemRepository;
}
