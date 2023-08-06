import java.net.*;
import java.io.*;

public class client{
	public static void main(String[] args) throws IOException{
		Socket s = new Socket("localhost", 4999);
		
		PrintWriter pr = new PrintWriter(s.getOutputStream());
		pr.println("I'm from client 1");
		pr.flush();
		
		// For one way communication above code is enough
		
		InputStreamReader in = new InputStreamReader(s.getInputStream());
		BufferedReader bf = new BufferedReader(in);
		
		String str = bf.readLine();
		System.out.println("sever: " + str);		
		
		
	}
}