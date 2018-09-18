package com.itheima.service;

import java.util.List;

import com.itheima.entity.Items;

public interface IItemsService {
	/**
	 * 查找所有的Items对象
	 * @return
	 */
	public List<Items> findAll();
	/**
	 * 根据id查找items对象
	 * @param id
	 */
	public Items findById(Integer id);
	/**
	 * 根据Items对象信息
	 * @param items
	 */
	public void updateItem(Items items);
	/**
	 * 保存
	 * @param items
	 */
	public void save(Items items);
}
