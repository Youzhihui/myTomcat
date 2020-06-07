package test;

/*
 * 存储参数用的    请求发来的东西都存在这里
 * 
 * */
public class HttpServletRequest {
	private String requestName;

	public HttpServletRequest(String requestName) {
		super();
		this.requestName = requestName;
	}

	public String getRequestName() {
		return requestName;
	}
	
	
}
