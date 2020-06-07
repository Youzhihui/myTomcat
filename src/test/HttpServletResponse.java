package test;

import java.io.PrintWriter;

public class HttpServletResponse {
	
	private PrintWriter writer;

	public HttpServletResponse(PrintWriter writer) {
		super();
		this.writer = writer;
	}

	public PrintWriter getWriter() {
		return writer;
	}

	
	
	
}
