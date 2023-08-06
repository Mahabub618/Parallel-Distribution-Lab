import java.net.*;
import java.io.*;

public class server{
	public static void main(String[] args) throws IOException{
		ServerSocket ss = new ServerSocket(4999);
		Socket s = ss.accept();
		
		System.out.println("A new client is connected");
		
		InputStreamReader in = new InputStreamReader(s.getInputStream());
		BufferedReader bf = new BufferedReader(in);
		
		String str = bf.readLine();
		System.out.println("client: " + str);
		
		//For one way communication above code is enough
		
		PrintWriter pr = new PrintWriter(s.getOutputStream());
		pr.println("I'm from server!");
		pr.flush();
	}
}