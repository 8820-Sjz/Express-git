package server;

import sqlUtils.Login;
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

    //µÇÂ¼/×¢²á
    public static void login(String line)throws Exception{
        Login login = new Login();
        char request = line.charAt(0);
        String s;
        if(request=='2'){//µÇÂ¼
            s = login.signIn(line);
        }else {//×¢²á
            s = login.signUp(line);
        }
        System.out.println("µÇÂ¼/×¢²áµÄ½á¹ûÊÇ£º"+s);
        pw.println(s);
        pw.flush();
    }
    //ËÑË÷/Ë¢ÐÂ
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
        System.out.println("¹²²éÑ¯µ½Êý¾Ý: "+list.size()+"Ìõ");
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
            System.out.println("ÊÕµ½ÇëÇó£º"+line);

            char request = line.charAt(0);

            //--------------ËÑË÷-----------------
            if(request == '0' || request == '1') {
                search(line);
            }

            //--------------µÇÂ¼/×¢²á------------------
            if(request=='2'||request=='3'){
                login(line);
            }


            is.close();
            pw.close();
            socket.close();

        }
    }
}
