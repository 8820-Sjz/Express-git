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

    //下单
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
            System.out.println("下单失败：数据不足！");
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
        System.out.println("下单成功！");
        pw.println("true");
        pw.flush();
    }

    //登录/注册
    public static void login(String line)throws Exception{
        Login login = new Login();
        char request = line.charAt(0);
        String result;
        char r = line.charAt(line.length()-1);
        String line2 = line.substring(0,line.length()-1);
        if(request=='2'){//登录
            result = login.signIn(line2,r);
        }else {//注册
            result = login.signUp(line2);
        }
        System.out.println("the result of login/sign is: "+result);
        pw.println(result);
        pw.flush();
    }
    //搜索/刷新
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
        System.out.println("共查询到数据: "+list.size()+"条");
        pw.println(list.size());
        pw.flush();
        for (String text: list) {
            pw.println(text);
            pw.flush();
        }

    }
    //详细界面信息获取
    public static void getDetail(String line) throws Exception{
        ArrayList<String> list = new ArrayList<>();
        list.clear();
        Search search = new Search();

        char request = line.charAt(0);
        String pid = line.substring(1);
        if(request== '1'){
            list = search.corespondingData1(pid);
        }else if(request== '2'){
            list = search.corespondingData2(pid);
        }else if(request== '3'){
            list = search.corespondingData3(pid);
        }else if(request== '4'){
            list = search.corespondingData4(pid);
        }
        System.out.println("共查询到数据: "+list.size()+"条");

        pw.println(list.size());
        pw.flush();

        for (String text: list) {
            pw.println(text);
            pw.flush();
        }

    }
    //详细信息修改
    public static void saveDetail(String line) throws Exception{
        Search search = new Search();
        String result = search.changeData(line);

        pw.println(result);
        pw.flush();
    }



    public static void serve() throws Exception {
        ServerSocket ss = new ServerSocket(11112);

        while (true) {
            socket = ss.accept();
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());

            String line = is.readLine();
            //if(line == null) break;
            System.out.println("get request: "+line);

            char request = line.charAt(0);

            //--------------搜索-----------------
            if(request == '0' || request == '1') {
                search(line);
            }

            //--------------登录/注册------------------
            if(request=='2'||request=='3'){
                login(line);
            }

            //--------------下单----------------
            if(request=='4'){
                order(line.substring(1));
            }

            //-----------获取详细信息-------------
            if(request=='5'){
                getDetail(line.substring(1));
            }

            //-----------详细信息修改------------
            if(request=='6'){
                saveDetail(line.substring(1));
            }

            is.close();
            pw.close();
            socket.close();

        }
    }
}
