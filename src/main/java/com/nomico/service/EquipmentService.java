package com.nomico.service;

import com.nomico.model.Equipment;

import java.util.List;


public interface EquipmentService {
	public List<Equipment> selectAll(Equipment record);
	public  void updateByPrimaryKeySelective(Equipment record) throws Exception;
	public  void insertSelective(Equipment record) throws Exception;
	public  Equipment selectByPrimaryKey(String id);
	
}
