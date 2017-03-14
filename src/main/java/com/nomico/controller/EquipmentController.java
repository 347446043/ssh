package com.nomico.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.StringUtil;
import com.nomico.model.Equipment;
import com.nomico.model.Equipmentname;
import com.nomico.service.EquipmentService;
import com.nomico.service.EquipmentnameService;
import com.nomico.util.DateUtil;
import com.nomico.util.GetIp;
import com.nomico.util.GetUuid;
import com.nomico.util.PageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;




@Controller
public class EquipmentController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1198039982255494786L;
	
	@Autowired
	private EquipmentService equipmentService;
	   @Autowired
		private EquipmentnameService equipmentnameService;
	

    @RequestMapping("equipment/list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page, Equipment equipment, HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
			
		List<Equipmentname>  equipmentnamelist=	equipmentnameService.selectAll(new Equipmentname());
		mav.addObject("equipmentnamelist", equipmentnamelist);
		if(null!=equipment){
			if(StringUtils.isNotBlank(equipment.getPpxh())){
				equipment.setPpxh("%"+equipment.getPpxh()+"%");
			}
			if(StringUtils.isNotBlank(equipment.getCcbh())){
				equipment.setCcbh("%"+equipment.getCcbh()+"%");
			}
			
		}	
		
	 if(StringUtil.isEmpty(page)){
			 page="1";
			}		 
		int pageNum= Integer.parseInt(page);  //把页面传过来的页码转成整型
        //pageNum  当前传过来的页码               15代表每页显示多少条
		PageHelper.startPage(pageNum,5);
		List<Equipment> list=	equipmentService.selectAll(equipment);
		//将查询结果封装到PageInfo，返回到页面
	    PageInfo pageinfo=new PageInfo(list);
	 String targetUrl=   PageUtil.getPage("list", pageinfo,1);   //log/loglist  要跳转的url   0  使用文字分页   1  使用bootstrap按钮分页
		mav.addObject("modeName", "设备管理");
		mav.addObject("pageCode", targetUrl);
		mav.addObject("list", list);
		mav.addObject("total", pageinfo.getTotal());
		mav.addObject("mainPage", "equipment/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
    @RequestMapping("equipment/addjsp")
	public ModelAndView addjsp(){
		ModelAndView model=new ModelAndView();
		List<Equipmentname> list=equipmentnameService.selectAll(new Equipmentname());
		model.addObject("mainPage", "equipment/save.jsp");
		model.addObject("modeName", "添加设备");
		model.addObject("list",list);
		model.setViewName("main");
		return model;
	}
    @RequestMapping("equipment/edituploadjsp")
	public ModelAndView edituploadjsp(HttpServletRequest  request){
		ModelAndView model=new ModelAndView();
		Equipment equipment=equipmentService.selectByPrimaryKey(request.getParameter("id"));
		
		model.addObject("mainPage", "equipment/saveupload.jsp");
		model.addObject("modeName", "修改上传文件");
		model.addObject("equipment",equipment);
		model.setViewName("main");
		return model;
	}

	@RequestMapping("equipment/save")
	public String insert(HttpServletRequest request){
		Equipment equipment=new Equipment();
		equipment.setEquipmentnameid(request.getParameter("equipmentnameid"));
		equipment.setCreatetime(new Date());
		equipment.setCreateip(GetIp.getIpAddr(request));
		equipment.setCcbh(request.getParameter("ccbh"));
		equipment.setPpxh(request.getParameter("ppxh"));
		equipment.setState(Integer.valueOf(request.getParameter("state")));
		equipment.setId(GetUuid.getUUID());
		String	checktime=request.getParameter("checktime");
		String  checkavltime=request.getParameter("checkavltime");
		String	jdjg=request.getParameter("jdjg");
		String	jdzsh=request.getParameter("jdzsh");
		if(StringUtils.isNotBlank(checktime)){
			equipment.setChecktime(DateUtil.fomatDatetime(checktime));
		}
		if(StringUtils.isNotBlank(checkavltime)){
			equipment.setCheckavltime(DateUtil.fomatDatetime(checkavltime));
		}
		if(StringUtils.isNotBlank(jdjg)){
			equipment.setJdjg(jdjg);
		}
		if(StringUtils.isNotBlank(jdzsh)){
			equipment.setJdzsh(jdzsh);
		}
		
		//创建一个通用的多部分解析器
				CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
				//判断 request 是否有文件上传,即多部分请求
				if(multipartResolver.isMultipart(request)){
					//转换成多部分request  
					MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
					//取得request中的所有文件名
					Iterator<String> iter = multiRequest.getFileNames();
					int t=1;
					while(iter.hasNext()){
						
						//记录上传过程起始时的时间，用来计算上传时间
					//	int pre = (int) System.currentTimeMillis();
						//取得上传文件
						MultipartFile file = multiRequest.getFile(iter.next());
						if(file != null){
							//取得当前上传文件的文件名称
							String myFileName = file.getOriginalFilename();
							String fileName=null;
							//如果名称不为“”,说明该文件存在，否则说明该文件不存在
							if(myFileName.trim() !=""){
							//	System.out.println(myFileName);
								//application/x-msdownload  exe
								//System.out.println("文件类型:"+file.getContentType());
								//重命名上传后的文件名
							fileName = DateUtil.getDayTime()+ file.getOriginalFilename();
								//定义上传路径
								String path=	request.getSession().getServletContext().getRealPath("/WEB-INF/upload")+"/"+fileName;
								//String path = request.getSession().getServletContext().getRealPath("/upload") + file.getOriginalFilename();
								File localFile = new File(path);
							
								try {
									file.transferTo(localFile);
								} catch (IllegalStateException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
							
							if(t==1){
								equipment.setFile1(fileName);
								t++;
							}
							if(t==2){
								equipment.setFile2(fileName);
								
							}

						
						}
						//记录上传该文件后的时间
					//	int finaltime = (int) System.currentTimeMillis();
						//System.out.println(finaltime - pre);
						
					}
					try {
						equipmentService.insertSelective(equipment);
						
					} catch (Exception e) {
						System.out.println("插入失败");
					}
					}
	return "redirect:/equipment/list.do";	
	}
	
	
	/*
	 *  @RequiresPermissions("system_parameter_view")
    @RequestMapping(value = {"/sp_upload"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> category_upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<String, Object>();

        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            //记录上传过程起始时的时间，用来计算上传时间
            //	int pre = (int) System.currentTimeMillis();
            //取得上传文件
            MultipartFile file = multiRequest.getFile(iter.next());
            if (file != null) {
                //取得当前上传文件的文件名称
                String myFileName = file.getOriginalFilename();
                String fileName = null;
                //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                if (myFileName.trim() != "") {
                    //	System.out.println(myFileName);
                    //application/x-msdownload  exe
                    //System.out.println("文件类型:"+file.getContentType());
                    //重命名上传后的文件名
                    String fileEnd = "";
                    if ("image/jpg".equals(file.getContentType()) || "image/jpeg".equals(file.getContentType())) {
                        fileEnd = "jpg";
                    } else if ("image/gif".equals(file.getContentType())) {
                        fileEnd = "gif";
                    } else if ("image/png".equals(file.getContentType())) {
                        fileEnd = "png";
                    } else {
                        fileEnd = "png";
                    }
                    Date date = new Date();
                    String time = Long.toString(date.getTime());
                    String filename = time + "." + fileEnd;
                    //定义上传路径
                    String path = request.getSession().getServletContext().getRealPath("") + "/" + filename;
                    //  String path = filepath+filename;
                    File localFile = new File(path);

                    try {
                        file.transferTo(localFile);
                        String serpath = ossObjectAli.aliuploadFile("purchase/goods", localFile, filename);

                        if (StringUtils.isNotBlank(serpath)) {
                            localFile.delete();
                            map.put("imgurl", serpath);
                            map.put("code", 1);
                        } else {
                            map.put("code", 0);
                        }
                    } catch (IllegalStateException e) {
                        logger.error("系统参数图片上传出错，" + e.getMessage(), e.getCause());
                        map.put("code", 0);
                    } catch (IOException e) {
                        logger.error("系统参数图片上传出错，" + e.getMessage(), e.getCause());
                        map.put("code", 0);
                    }

                }
            }
        }
        return map;
    }
	 * 
	 */
//	@RequestMapping("/download")
//    public String download(@RequestParam(value="fileid",required=false)String fileid, HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("multipart/form-data");
//        response.setHeader("Content-Disposition", "attachment;fileName="+ new String(fileid.getBytes("utf-8"), "ISO8859-1"));
//        try {
//        	String path=	request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
//           // String path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "download";//这个download目录为啥建立在classes下的
//            InputStream inputStream = new FileInputStream(new File(path+ File.separator + fileid));
//            OutputStream os =  new BufferedOutputStream(response.getOutputStream());
//            byte[] b = new byte[20480];
//            int length;
//            while ((length = inputStream.read(b)) > 0) {
//                os.write(b, 0, length);
//            }
//     // os.flush();
//             // 这里主要关闭。
//            os.close();
//                 
//            
//            inputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//            //  返回值要注意，要不然就出现下面这句错误！
//            //java+getOutputStream() has already been called for this response
//        return null;
//    }
//	
	
	@RequestMapping("download") 
    public void down(String fileid, HttpServletRequest request, HttpServletResponse response) throws IOException  {   
  
        response.setContentType("text/html;charset=utf-8");   
        request.setCharacterEncoding("UTF-8");   
       BufferedInputStream bis = null;   
        BufferedOutputStream bos = null;   
  
        String ctxPath =	request.getSession().getServletContext().getRealPath("/WEB-INF/upload")+"/";   
        String downLoadPath = ctxPath + fileid;   
      //  System.out.println(downLoadPath);   
        try {   
            long fileLength = new File(downLoadPath).length();   
            response.setContentType("application/x-msdownload;");   
            response.setHeader("Content-disposition", "attachment; fileName="+ new String(fileid.getBytes("utf-8"), "ISO8859-1"));   
            response.setHeader("Content-Length", String.valueOf(fileLength));   
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));   
            bos = new BufferedOutputStream(response.getOutputStream());   
            byte[] buff = new byte[20480];   
            int bytesRead;   
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {   
                bos.write(buff, 0, bytesRead);   
            }   
        } catch (FileNotFoundException e) {
			//System.out.println("文件未找到");
		//	e.printStackTrace();
			  response.setHeader("Content-disposition", "attachment; fileName="+ new String("文件未找到".getBytes("utf-8"), "ISO8859-1"));   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
        finally {   
            if (bis != null)   
                bis.close();   
            if (bos != null)   
                bos.close();   
        }   
      //  return null;   
    }   
	
	@RequestMapping("equipment/edit")
	public ModelAndView edit(HttpServletRequest request){
		ModelAndView model=new ModelAndView();
		String id=	request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			Equipment equipment=	equipmentService.selectByPrimaryKey(id);
			if(null!=equipment){
				List<Equipmentname> list=equipmentnameService.selectAll(new Equipmentname());
				model.addObject("equipment", equipment);
				model.addObject("list", list);
			}else{
				System.out.println("设备不存在");
			}
		
		}
		model.addObject("mainPage", "equipment/edit.jsp");
		model.addObject("modeName", "修改设备信息");
		model.setViewName("main");
		return model;
	}









}
