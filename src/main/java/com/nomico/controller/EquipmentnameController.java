package com.nomico.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.StringUtil;
import com.nomico.model.Equipmentname;
import com.nomico.service.EquipmentnameService;
import com.nomico.util.PageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Controller
public class EquipmentnameController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 491561301256179891L;
    @Autowired
	private EquipmentnameService equipmentnameService;
	
    @RequestMapping("equipmentname/list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page, Equipmentname equipmentname, HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		
		if(null!=equipmentname){
			if(StringUtils.isNotBlank(equipmentname.getName())){
				equipmentname.setName(("%"+equipmentname.getName()+"%"));
			}
		}
		
		
	 if(StringUtil.isEmpty(page)){
			 page="1";
			}		 
		int pageNum= Integer.parseInt(page);  //把页面传过来的页码转成整型
        //pageNum  当前传过来的页码               15代表每页显示多少条
		PageHelper.startPage(pageNum,5);
		List<Equipmentname> list=	equipmentnameService.selectAll(equipmentname);
		//将查询结果封装到PageInfo，返回到页面
		 PageInfo pageinfo=new PageInfo(list);
		if(list.size()>0){
		    String targetUrl=   PageUtil.getPage("list", pageinfo,1);   //log/loglist  要跳转的url   0  使用文字分页   1  使用bootstrap按钮分页
			mav.addObject("pageCode", targetUrl);
		}
		mav.addObject("modeName", "设备名称管理");
		mav.addObject("list", list);
		mav.addObject("total", pageinfo.getTotal());
		mav.addObject("mainPage", "equipmentname/list.jsp");
		mav.setViewName("main");
		return mav;
	}
    
    @RequestMapping("equipmentname/addjsp")
	public ModelAndView addjsp(){
		ModelAndView model=new ModelAndView();
		
		model.addObject("mainPage", "equipmentname/save.jsp");
		model.addObject("modeName", "添加设备名称");
		model.setViewName("main");
		
		
		return model;
	}
	@RequestMapping("equipmentname/editdep")
	public ModelAndView editjsp(HttpServletRequest request){
		ModelAndView model=new ModelAndView();
		
	String id=	request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			Equipmentname equipmentname=	equipmentnameService.selectByPrimaryKey(id);
			if(null!=equipmentname){
				model.addObject("equipmentname", equipmentname);
			}else{
				System.out.println("设备名称编号不存在");
			}
		
		}
		model.addObject("mainPage", "equipmentname/edit.jsp");
		model.addObject("modeName", "修改设备名称信息");
		model.setViewName("main");
		return model;
	}
}
