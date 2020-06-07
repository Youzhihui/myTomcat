package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestTomcat {

	public static void main(String[] args) {
		
		try {
			//1.自己创建一个"小服务"ServreSocket
			System.out.println("===server start====");
			ServerSocket server = new ServerSocket(9999);
			//2、等待某个客户端过来连接我
			while(true) {
				Socket socket = server.accept();
				//启动一个小线程小弟  让它负责当前socket对应浏览器的读写操作
				Handler handler = new Handler(socket);
				handler.start();//线程进入就绪状态
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
