package com.cjhme.system.fileupload.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cjhme.system.main.bean.ResultData;
import com.cjhme.system.main.constant.CommonConstants;
import com.cjhme.system.main.util.ConfigUtil;
import com.cjhme.system.main.util.UploadImgUtil;

/**
 * 
 * <p>  
 * Title: FileUploadAction.java 
 * </p>  
 * Description: 文件上传
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Apr 6, 2015 9:13:07 AM
 */
@Controller
@Scope("prototype")
@RequestMapping("/fileUpload")
public class FileUploadAction {

	Log LOGGER = LogFactory.getLog(FileUploadAction.class);
	
	
	
	/**
	 * 图片上传
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/uploadImg",method={RequestMethod.POST,RequestMethod.GET})
	public void uploadImg(HttpServletRequest request,HttpServletResponse response){
		ResultData resultData=null;
		try{
			String formFileName="imgUpload";
			String commonImagesPath = ConfigUtil.getInstance().getPopString("commonImagesPath");
			
			LOGGER.info("图片保存地址——>"+commonImagesPath);
			resultData= UploadImgUtil.upload(request, formFileName, commonImagesPath,false);
			LOGGER.info("图片上传成功！");
		}catch(Exception e){
			e.printStackTrace();
			resultData=new ResultData();
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("图片上传失败！");
			LOGGER.error("图片上传失败,错误——>"+e);
		}
		resultData.printJsonData(response,"text/html;charset=utf-8");
	}
	
	/**
	 * 图片上传重新生成文件名
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/uploadImgByNewFileName",method={RequestMethod.POST,RequestMethod.GET})
	public void uploadImgByNewFileName(HttpServletRequest request,HttpServletResponse response){
		ResultData resultData=null;
		try{
			
			String formFileName="imgUpload";
			String commonImagesPath = ConfigUtil.getInstance().getPopString("commonImagesPath");
			LOGGER.info("图片保存地址——>"+commonImagesPath);
			
			resultData= UploadImgUtil.upload(request, formFileName, commonImagesPath,true);
			LOGGER.info("图片上传成功！");
		}catch(Exception e){
			e.printStackTrace();
			resultData=new ResultData();
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("图片上传失败！");
			LOGGER.error("图片上传失败,错误——>"+e);
		}
		resultData.printJsonData(response,"text/html;charset=utf-8");
	}
	
	
	/**
	 * 图片上传压缩处理
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/uploadImgCompression",method={RequestMethod.POST,RequestMethod.GET})
	public void uploadImgCompression(HttpServletRequest request,HttpServletResponse response){
		ResultData resultData=null;
		try{
			//是否压缩Y(压缩)，N(正常)
			String isCompress=request.getParameter("isCompress");
			if(null==isCompress || "".equals(isCompress) || "null".equals(isCompress)){
				isCompress="N";
			}
			
			//宽度
			String width=request.getParameter("width");
			if(null==width || "".equals(width) || "null".equals(width)){
				width="1";
			}
			
			//高度
			String height=request.getParameter("height");
			if(null==height || "".equals(height) || "null".equals(height)){
				height="1";
			}
			
			//是否重新命名Y(重新命名)或N(不重新命名)
			String isNewFileName=request.getParameter("isNewFileName");
			if(null==isNewFileName || "".equals(isNewFileName) || "null".equals(isNewFileName)){
				isNewFileName="N";
			}
			
			
			String formFileName="imgUpload";
			String systemIconPath = ConfigUtil.getInstance().getPopString("commonImagesPath");
			LOGGER.info("图片保存地址——>"+systemIconPath);
			resultData= UploadImgUtil.uploadImgCompression(request, formFileName, systemIconPath, isCompress, Integer.parseInt(width), Integer.parseInt(height), isNewFileName);
			LOGGER.info("图片上传压缩处理成功！");
		}catch(Exception e){
			e.printStackTrace();
			resultData=new ResultData();
			resultData.setResultType(CommonConstants.RESULT_ERROR);
			resultData.setResultMsg("图片上传压缩处理失败！");
			LOGGER.error("图片上传压缩处理失败,错误——>"+e);
		}
		resultData.printJsonData(response,"text/html;charset=utf-8");
	}
	
}
