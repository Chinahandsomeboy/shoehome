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
        //必须完全和字段名一致 希望可以设置别名
        itemQuery.leftJoin("itemSmallType");
        itemQuery.leftJoin("itemLargeType");
        itemQuery.and(QueryParamsFilter.eq("name","洗衣服"),
                QueryParamsFilter.eq("itemSmallType.name","wash1"),
                QueryParamsFilter.eq("itemLargeType.name","wash"));


        return getItemRepository().findAll(itemQuery);
    }
}
