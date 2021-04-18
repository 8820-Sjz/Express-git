package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    //static ExecutorService pool = Executors.newFixedThreadPool(4);
    
	private Socket socket;
    private PrintWriter pw;
    private BufferedReader is;
	
    public Client() {
    	
    }

    public String login(String request) throws Exception {
    	newServe();
    	
    	pw.println(request);
    	pw.flush();
    	
    	String line = is.readLine();
 	
    	is.close();
    	pw.close();
    	socket.close();
    	
    	return line;
    }
    
    public ArrayList<String> search(String request) throws Exception {
    	newServe();
    	ArrayList<String> list = new ArrayList<>();
        
    	pw.println(request);
    	pw.flush();
    	
        String line = is.readLine();
        int size = Integer.parseInt(line);
        for(int i = 0; i<size; i++) {
        	line = is.readLine();
        	System.out.println(line);
        	list.add(line);
        }System.out.println("共读取数据："+size+"条");   
        socket.close();
		return list;
    }
    
    public void newServe() throws Exception {
    	//long start = System.currentTimeMillis();
    	
        socket = new Socket("127.0.0.1",11112);
        pw = new PrintWriter(socket.getOutputStream());
        is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        System.out.println("-----------I have a request--------------");                     	             
        //System.out.println("Consumer end, time:" + (System.currentTimeMillis() - start) / 1000 + "s");
    }
    

}
