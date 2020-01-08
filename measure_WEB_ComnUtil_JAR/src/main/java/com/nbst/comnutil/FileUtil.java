package com.nbst.comnutil;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {
	public static final String MACHINE_PIC_UPLOAD_PATH = "/uploads/machine/img";
	public static final String MACHINE_PIC_CD_PREFIX = "machine_pic_";
	public static final String WORKSHOP_PIC_UPLOAD_PATH = "/uploads/workshop/img";
	public static final String WORKSHOP_PIC_CD_PREFIX = "workshop_pic_";
	// 有效的文件后缀
	private static List<String> validedFileNameList = Arrays.asList("jpg", "gif", "bmp", "png", "jpeg", "doc", "docx",
			"xls", "xlsx", "ppt", "pptx", "swf", "rar", "zip", "pdf", "txt");

	// 有效的图片文件后缀
	private static List<String> validedPicFileNameList = Arrays.asList("jpg", "gif", "bmp", "png", "jpeg");

	// 是否为有效的图片文件
	public static Boolean validatePicFileType(MultipartFile file) {
		String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1).toLowerCase();// 获取文件后缀
		if (!validedPicFileNameList.contains(type))
			return false;
		return true;
	}

	public static String getSaveName(String fileName) {
		return Tools.generateSequenceNo(MACHINE_PIC_CD_PREFIX)
				+ fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
	}

	public static String getPath(MultipartFile file, String contextRealPath, String contextPath) {
		// TODO Auto-generated method stub
		// 上传时的文件名
//		String fileName = file.getOriginalFilename();
		// 获取项目所在绝对路径+图片保存路径
		String path = contextRealPath;
		log.debug("保存路径：" + path);
		// request.getSession().getServletContext().getRealPath(MACHINE_PIC_UPLOAD_PATH);
		// 图片的保存文件名
		// String saveName = Tools.generateSequenceNo(MACHINE_PIC_CD_PREFIX)
		// + fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		// 图片url
		String realPath = contextPath + MACHINE_PIC_UPLOAD_PATH + "/" /* + saveName */;
		log.debug("保存实际路径：" + realPath);
		return realPath;
	}
	public static boolean save(MultipartFile file, String contextRealPath,String saveName){
		File targetFile = new File(contextRealPath, saveName);
		if (!targetFile.getParentFile().exists()) {
			targetFile.getParentFile().mkdirs();
		}

		try {
			file.transferTo(targetFile);//传送文件
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
