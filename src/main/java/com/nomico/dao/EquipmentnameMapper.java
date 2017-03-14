package com.nomico.dao;

import com.nomico.model.Equipmentname;

import java.util.List;


public interface EquipmentnameMapper {
	public   int deleteByPrimaryKey(String id);

	public   int insert(Equipmentname record);

	public  int insertSelective(Equipmentname record);

	public  Equipmentname selectByPrimaryKey(String id);
    public  int updateByPrimaryKeySelective(Equipmentname record);
    public List<Equipmentname> selectAll(Equipmentname record);
    public int updateByPrimaryKey(Equipmentname record);
}