package com.itheima.service;

import java.util.List;

import com.itheima.entity.Items;

public interface IItemsService {
	/**
	 * �������е�Items����
	 * @return
	 */
	public List<Items> findAll();
	/**
	 * ����id����items����
	 * @param id
	 */
	public Items findById(Integer id);
	/**
	 * ����Items������Ϣ
	 * @param items
	 */
	public void updateItem(Items items);
	/**
	 * ����
	 * @param items
	 */
	public void save(Items items);
}
