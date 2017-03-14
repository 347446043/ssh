package com.nomico.service.impl;

import java.util.List;

import com.nomico.dao.EquipmentnameMapper;
import com.nomico.model.Equipmentname;
import com.nomico.service.EquipmentnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("equipmentnameService")
public class EquipmentnameServiceImpl implements EquipmentnameService {

	@Autowired
	private EquipmentnameMapper equipmentnameMapper;
	@Override
	public List<Equipmentname> selectAll(Equipmentname record) {
		// TODO Auto-generated method stub
		return equipmentnameMapper.selectAll(record);
	}

	@Override
	public void insertSelective(Equipmentname record) throws Exception {
		  if(1!=equipmentnameMapper.insertSelective(record)){
		    	throw new Exception();
		    }

	}

	@Override
	public void updateByPrimaryKeySelective(Equipmentname record)
			throws Exception {
		  if(1!=equipmentnameMapper.updateByPrimaryKeySelective(record)){
		    	throw new Exception();
		    }

	}

	@Override
	public Equipmentname selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return equipmentnameMapper.selectByPrimaryKey(id);
	}

}
