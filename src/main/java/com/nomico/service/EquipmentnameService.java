package com.nomico.service;

import com.nomico.model.Equipmentname;

import java.util.List;


public interface EquipmentnameService {
    public List<Equipmentname> selectAll(Equipmentname record);
	public  Equipmentname selectByPrimaryKey(String id);
    public  void insertSelective(Equipmentname record) throws Exception;
    public  void updateByPrimaryKeySelective(Equipmentname record) throws Exception;
    
}
