package com.nomico.service.impl;

import java.util.List;

import com.nomico.dao.EquipmentMapper;
import com.nomico.model.Equipment;
import com.nomico.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("equipmentService")
public class EquipmentServiceImpl implements EquipmentService {

@Autowired
private EquipmentMapper equipmentMapper;
	
	public List<Equipment> selectAll(Equipment record) {
		return equipmentMapper.selectAll(record);
	}

	@Override
	public void updateByPrimaryKeySelective(Equipment record) throws Exception{
		  if(1!=equipmentMapper.updateByPrimaryKeySelective(record)){
		    	throw new Exception();
		    }
	}

	public void insertSelective(Equipment record)  throws Exception{
		   if(1!=equipmentMapper.insertSelective(record)){
		    	throw new Exception();
		    }
	}

	public Equipment selectByPrimaryKey(String id) {
		return equipmentMapper.selectByPrimaryKey(id);
	}



}
