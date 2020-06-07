package controller;

import java.io.PrintWriter;

import test.HttpServlet;
import test.HttpServletRequest;
import test.HttpServletResponse;

/**
 * 这个以后是我们以后的控制层
 * 控制的是相应信息----最后给浏览器展示的内容String
 * 给浏览器展示   需要让浏览器能懂的String  不能随便写
 * 浏览器只懂一种结果的字符串    HTML
 * 
 * */

public class IndexController extends HttpServlet{

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("这是你们要找的资源");
		//4.将最终的信息相应返回浏览器
		//  写回去    输出流   怎么创建
		PrintWriter out = response.getWriter();
		//用out对象向浏览器写回数据
		//  响应头
		//  版本号   状态码(200)   OK
		//  告诉浏览器要如何解析   字符集什么
		//  空行
		//  响应数据
		
		//我们想要写回去的数据不是在这里拼接的
		//是之前提前写好了的一个html形式的文件      文件内容     index.html
		//动态数据   数据库读出来的   动态数据
		//先把静态数据先响应回去     动态数据在过去
		out.write("HTTP1.1 200 ok\r\n");
		out.write("Content-Type:text/html;charset-UTF-8\r\n");
		out.write("\r\n");
		out.write("<html>");
		out.write("		<body>");
		out.write("		hello,Tomcat<br>");
		out.write("		<input type='button',value='YES'></input>");	
		out.write("		</body>");
		out.write("</html>");
		out.flush();
	}
}
