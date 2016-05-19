package en.edu.qdu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestSockServer {
	public static void main(String[] args) {
		Socket socket = null;
		
		try {
			ServerSocket s = new ServerSocket(8888);
			System.out.println("服务器启动");
			// 连接
			while (true) {
				socket = s.accept();
				 Clinet c=new TestSockServer().new Clinet(socket);
				 c.start();
			}
		} catch (Exception e) {
			System.out.println("server over");
		} 
	}
	
	class Clinet extends Thread{
		DataOutputStream dos = null;
		Socket socket = null;
		DataInputStream dis = null;
		public Clinet(Socket socket) {
			this.socket = socket;
		}
		
		public void run(){
			try {
				dos = new DataOutputStream(socket.getOutputStream());
				dis = new DataInputStream(socket.getInputStream());
				String str = null;
				if ((str = dis.readUTF()) != null) {
					System.out.println("客户端:" + str);
				}
				(new DataOutputStream(socket.getOutputStream())).writeUTF(str);
				dos.writeUTF(socket.getPort()+" say :" + str);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					dis.close();
					dos.close();
					socket.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		
		
	}
	

}
