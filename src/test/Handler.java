package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 这个类是服务器的小弟
 * 它是一个线程
 * 负责某一个浏览器的交互
 * 是一个线程    is-a
 * 
 */
public class Handler extends Thread{
	
	//属性
	private Socket socket;
	//构造方法
	public Handler(Socket socket) {
		this.socket = socket;
	}
	
	//重写的方法  run方法就是小弟做的事情
	public void run() {
		try {
			this.receiveRequset();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//1.读取/接收请求----String
	private void receiveRequset() throws Exception {
		//通过socket创建一个输入流
		InputStream inputStream = socket.getInputStream();
		//将字节流转化成一个字符流
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		//为了读取协议  按照一行一行的格式读取  包装成Buffered高级流  读取一行
		BufferedReader reader = new BufferedReader(inputStreamReader);
		//读取请求数据
		String requestContent = reader.readLine();
		//找一个小弟帮我们去解析
		this.parseRequest(requestContent);//只需要第一行的请求数据
		
//		while(null!=requestContent && !"".equals(requestContent)) {
//			System.out.println(requestContent);
//			requestContent = reader.readLine();
			/*
			 * 得到的请求数据
			----------------------------
			GET /?fagnasjdfjfja HTTP/1.1
			Host: localhost:9999
			Connection: keep-alive
			Cache-Control: max-age=0
			Upgrade-Insecure-Requests: 1
			User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36
			Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,* *;q=0.8,application/signed-exchange;v=b3;q=0.9
			Sec-Fetch-Site: none
			Sec-Fetch-Mode: navigate
			Sec-Fetch-User: ?1
			Sec-Fetch-Dest: document
			Accept-Encoding: gzip, deflate, br
			Accept-Language: zh-CN,zh;q=0.9
			Cookie: Idea-1f3975f2=5654498c-9240-45a0-ac81-ceee4b8104af
			---------------------------
			*/
		
//		}
		//解析数据
		//请求方式                               ----->String类型的变量
		//请求资源名                           ----->String类型的变量
		//解析所有的key:value  ----->Map
		//解析后的数据统一包装在一起HttpServletRequest
	}
	//2.解析请求中携带的数据
	private void parseRequest(String requestContent) throws Exception {
		//请求方式   请求资源   版本号
		String[] oneLineValue = requestContent.split(" ");
		String requestType = oneLineValue[0];
		String requestName = oneLineValue[1];
		String httpType = oneLineValue[2];
		//===================================
		//请求的名字找资源---是一个Servlet类(方法  执行)
		//肯定需要反射
		
		HttpServletRequest request = new HttpServletRequest(requestName);
		HttpServletResponse response = new HttpServletResponse(new PrintWriter(socket.getOutputStream()));
		this.findResource(request,response);
	}
	//3.根据解析后的请求名---找资源（Servlet类  对象  方法  执行后结束--相应信息）
	private void findResource(HttpServletRequest request,HttpServletResponse response) throws Exception {
		if("/index".equals(request.getRequestName())) {
			//直接找那个IndexController类
			Class clazz = Class.forName("controller.IndexController");
			//通过反射找寻clazz中的那个方法
			Method method = clazz.getMethod("service",HttpServletRequest.class,HttpServletResponse.class);
			//让方法执行
			Object obj = clazz.newInstance();
			method.invoke(obj,request,response);
		}
	}
	//4.将最终的信息相应回浏览器
}
