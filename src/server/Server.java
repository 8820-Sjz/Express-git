package server;

import sqlUtils.Login;
import sqlUtils.Order;
import sqlUtils.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
    static DBConnection dbc;
    static Socket socket;
    static BufferedReader is;
    static PrintWriter pw;

    public static void main(String[] args) throws Exception {
        serve();
    }

    //�µ�
    public static void order(String num)throws Exception{
        int size = Integer.parseInt(num);
        ArrayList<String> list = new ArrayList<>();
        String line;
        for(int i=0; i<size; i++){
            line = is.readLine();
            if(line==""||line==null)break;
            list.add(line);
        }
        //System.out.println("new data: "+list.size());
        for (String s:list) {
            System.out.println(s);
        }
        if(list.size()!=size){
            pw.println("false");
            System.out.println("�µ�ʧ�ܣ����ݲ��㣡");
            return;
        }
        Order order = new Order();

        order.newPackage(list.get(0));
        order.newReciver(list.get(1));
        order.newDelivery();
        order.newDetail(list.get(2));
        order.newPDetail();
        order.newLoads();
        order.newOrders(list.get(3),list.get(4));
        order.newDeal(list.get(4));
        System.out.println("�µ��ɹ���");
        pw.println("true");
        pw.flush();
    }

    //��¼/ע��
    public static void login(String line)throws Exception{
        Login login = new Login();
        char request = line.charAt(0);
        String s;
        if(request=='2'){//��¼
            s = login.signIn(line);
        }else {//ע��
            s = login.signUp(line);
        }
        System.out.println("��¼/ע��Ľ���ǣ�"+s);
        pw.println(s);
        pw.flush();
    }
    //����/ˢ��
    public static void search(String line) throws Exception{
        ArrayList<String> list = new ArrayList<>();
        list.clear();
        Search search1 = new Search();


        char request = line.charAt(0);
        if(request== '0'){
            list = search1.mainDataSearch();
        }else{
            list = search1.mainDataSearch(line.substring(1));
        }
        System.out.println("����ѯ������: "+list.size()+"��");
        pw.println(list.size());
        pw.flush();
        for (String text: list) {
            pw.println(text);
            pw.flush();
        }

    }


    public static void serve() throws Exception {
        ServerSocket ss = new ServerSocket(11112);

        while (true) {
            socket = ss.accept();
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());

            String line = is.readLine();
            //if(line == null) break;
            System.out.println("�յ�����"+line);

            char request = line.charAt(0);

            //--------------����-----------------
            if(request == '0' || request == '1') {
                search(line);
            }

            //--------------��¼/ע��------------------
            if(request=='2'||request=='3'){
                login(line);
            }

            //--------------�µ�----------------
            if(request=='4'){
                order(line.substring(1));
            }

            is.close();
            pw.close();
            socket.close();

        }
    }
}
