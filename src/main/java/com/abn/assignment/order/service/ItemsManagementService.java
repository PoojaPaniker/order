package com.abn.assignment.order.service;

import com.abn.assignment.order.model.Items;
import com.abn.assignment.order.repository.ItemsManagementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ItemsManagementService {

    @Autowired
    private ItemsManagementRepo itemRepo;

    public void addItems(List<Items> items){
        itemRepo.saveAll(items);
    }

    public Items getItem(int itemId){
        return itemRepo.findById(itemId).get();
    }
    public List<Items> getAllItems(){
        List<Items> item = new ArrayList<>();
        itemRepo.findAll().forEach(e -> item.add(e));
        return item;
    }

}
