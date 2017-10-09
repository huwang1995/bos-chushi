package cn.itcast.bos.web.action.base;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.web.action.common.BaseAction;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class TestUploadAction extends BaseAction<T> {

	private static final Logger LOGGER = Logger.getLogger(TestUploadAction.class);

	// 接收上传文件
	private File file3;
	private String file3FileName;
	private String file3ContentType;

	public void setFile3(File file3) {
		this.file3 = file3;
	}

	public void setFile3FileName(String file3FileName) {
		this.file3FileName = file3FileName;
	}

	public void setFile3ContentType(String file3ContentType) {
		this.file3ContentType = file3ContentType;
	}

	@Action(value = "testUpload", results = {
			@Result(name = "success", type = "redirect", location = "easyuidemo/testUpload.html") })
	public void testUpload() {
		System.out.println("文件：" + file3);
		System.out.println("文件名：" + file3FileName);
		System.out.println("文件类型：" + file3ContentType);

		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			// ServletActionContext.getResponse().getWriter()
			// .println("上传文件成功!");

			String savePath = ServletActionContext.getServletContext().getRealPath("/upload/");
			String saveUrl = ServletActionContext.getRequest().getContextPath() + "/upload/";

			System.out.println("upload savePath=" + savePath);
			System.out.println("upload saveUrl=" + saveUrl);

			// 生成随机图片名
			UUID uuid = UUID.randomUUID();
			String ext = file3FileName.substring(file3FileName.lastIndexOf("."));
			String randomFileName = uuid + ext;

			// 保存图片 (绝对路径)
			File destFile = new File(savePath + "/" + randomFileName);
			FileUtils.copyFile(file3, destFile);

			System.out.println("保存后的文件名=" + destFile.getName());
			System.out.println("保存后的文件路径=" + destFile.getPath());
			System.out.println("保存后的文件绝对路径=" + destFile.getAbsolutePath());

			ServletActionContext.getResponse().getWriter()
					.println("<img src='http://localhost:9001/bos_management/upload/" + destFile.getName() + "' />");

			// ServletActionContext.getResponse().getWriter()
			// .println("<img
			// src='http://localhost:9001/bos_management/upload/testUpload.jpg'
			// />");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
