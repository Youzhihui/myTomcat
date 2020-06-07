package test;

/*
 * 这个类的出现是规范所有Controller资源的方法名字统一的
 * 
 * */
public abstract class HttpServlet {
	public abstract void service(HttpServletRequest request,HttpServletResponse response);
}
