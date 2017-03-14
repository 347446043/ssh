package com.nomico.dao;

import com.nomico.model.Equipment;

import java.util.List;


public interface EquipmentMapper {
	public   int deleteByPrimaryKey(String id);

	public  int insert(Equipment record);

	public  int insertSelective(Equipment record);

	public List<Equipment> selectAll(Equipment record);
	
	public  Equipment selectByPrimaryKey(String id);

	public  int updateByPrimaryKeySelective(Equipment record);

	public  int updateByPrimaryKey(Equipment record);
}