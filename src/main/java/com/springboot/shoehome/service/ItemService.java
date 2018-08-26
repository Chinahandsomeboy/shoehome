package com.springboot.shoehome.service;

import com.springboot.shoehome.domain.Item;
import com.springboot.shoehome.repository.ItemRepository;
import com.springboot.shoehome.utils.Query;
import com.springboot.shoehome.utils.QueryParamsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by acer on 2018/7/28.
 */
@Service
public class ItemService {

    @Autowired private ItemRepository itemRepository;

    private ItemRepository getItemRepository(){
        return itemRepository;
    }

    public void addItem(Item item){
        getItemRepository().save(item);
    }

    public List getItem(){
        Query<Item> itemQuery = new Query<>();
        itemQuery.leftJoin("itemsmalltype");
        itemQuery.leftJoin("itemlargetype");
        itemQuery.and(QueryParamsFilter.eq("name","洗衣服"),
                QueryParamsFilter.eq("itemsmalltype.name","ship1"));

        return getItemRepository().findAll(itemQuery);
    }
}
