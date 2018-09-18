package com.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.entity.Items;
import com.itheima.mapper.ItemsMapper;
import com.itheima.service.IItemsService;
@Service
public class ItemsService implements IItemsService {

	@Autowired
	private ItemsMapper itemsMapper;
	public List<Items> findAll(){
		return itemsMapper.selectByExampleWithBLOBs(null);
	}
	@Override
	public Items findById(Integer id) {
		return itemsMapper.selectByPrimaryKey(id);
	}
	@Override
	public void updateItem(Items items) {
		itemsMapper.updateByPrimaryKeyWithBLOBs(items);
	}
	@Override
	public void save(Items items) {
		itemsMapper.insertSelective(items);
	}
}
