package model;

import obj.TransportInfo;

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
    public static String cid = null ;
    public Client() {

    }

    public String changeData(String sql) throws Exception {
        newServe();

        pw.println("6"+sql);
        pw.flush();

        String line = is.readLine();
        is.close();
        pw.close();
        socket.close();
        System.out.println("保存结果: "+line);
        return line;
    }

    public TransportInfo corespondingData4(int pid) throws Exception{
        ArrayList<String> list = new ArrayList<>();
        list = corespondingData(pid,"4");

        String arrivalTime = "";
        String previousPos = "";
        String currentPos = "";
        String transportation = "";
        String parcelStatus = "";
        TransportInfo info = null;

        //目前为止的数据库只能查询出一条
        for(int i=0; i<list.size(); i++) {
            String[] s = list.get(i).split(",");
            if(s.length!=5) {System.out.println("详细信息4获取出错!");return null;}
            arrivalTime = s[0];
            previousPos = s[1];
            currentPos = s[2];
            transportation = s[3];
            parcelStatus = s[4];
            info = new TransportInfo(arrivalTime,previousPos,currentPos,transportation,parcelStatus);
        }
        return info;
    }

    public ArrayList<String> corespondingData(int pid,String kind) throws Exception{
        newServe();
        ArrayList<String> list = new ArrayList<>();

        String request = "5"+kind+pid;
        pw.println(request);
        pw.flush();

        String line = is.readLine();
        int size = Integer.parseInt(line);
        for(int i = 0; i<size; i++) {
            line = is.readLine();
            System.out.println(line);
            list.add(line);
        }System.out.println("共读取详细信息"+kind+"："+size+"条");
        is.close();
        pw.close();
        socket.close();
        return list;
    }


    public String newOrder(ArrayList<String> list) throws Exception {
        newServe();

        pw.println("4"+list.size());
        pw.flush();

        for (String string : list) {
            pw.println(string);
            pw.flush();
        }

        String line = is.readLine();

        is.close();
        pw.close();
        socket.close();
        System.out.println("下单结果: "+line);
        return line;

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
