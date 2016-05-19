package en.edu.qdu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {
	public static void main(String[] args) {
		DataInputStream dis = null;
		DataOutputStream dos = null;
		Socket s1 = null;
		Scanner input = new Scanner(System.in);
		try {
			s1 = new Socket("127.0.0.1", 8888);
			String str = null;
			do {
				dos = new DataOutputStream(s1.getOutputStream());
				dis = new DataInputStream(s1.getInputStream());
				str = input.nextLine();
				dos.writeUTF(str);
				System.out.println(dis.readUTF());
			} while (!str.equals("88"));

		} catch (Exception e) {
			System.out.println("Client over");
		} finally {
			try {
				dis.close();
				dos.close();
				s1.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
