package com.shimne.zoopu.common.manage;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shimne.zoopu.util.ueditor.ActionEnter;

@Controller
@RequestMapping("/ueditor")
public class UeditorUploadAction
{
	@ResponseBody
	@RequestMapping(value = "/upload")
	public void upload(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("gbk");
			response.setHeader("Content-Type" , "text/html");

			String rootPath = request.getSession().getServletContext().getRealPath("/");

			String exec = new ActionEnter(request, rootPath).exec();
			PrintWriter out = response.getWriter();
			out.write(exec);
			out.flush();
			out.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}