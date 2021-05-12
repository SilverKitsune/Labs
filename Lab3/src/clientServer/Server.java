package clientServer;

import java.io.*;
import java.net.*;
import java.util.*;

///TODO Обработка текста
///TODO Запись в файл

public class Server {

    public static void main(String[] args) {

        ServerSocket ss;
        Socket s;
        InputStream is;
        OutputStream os;
        try {
            System.out.println("Socket Server Application");
        } catch (Exception ioe) {
            System.out.println(ioe.toString());
        }
        try {
            ss = new ServerSocket(7777);
            s = ss.accept();
            is = s.getInputStream();
            os = s.getOutputStream();
            int length;
            while (true) {
                byte[] buf = new byte[512];
                length = is.read(buf);
                if (length == -1)
                    break;
                String str = new String(buf, 0, buf.length);
                //StringTokenizer st;
                //st = new StringTokenizer(str, "\r\n");
                //str = (String) st.nextElement();
                System.out.println(">  " + str);
                os.write(buf, 0, buf.length);
                os.flush();
            }
            is.close();
            os.close();
            s.close();
            ss.close();
        } catch (Exception ioe) {
            System.out.println(ioe.toString());
        }
        try {
            System.out.println("Press Enter to terminate application...");
            System.in.read();
        } catch (Exception ioe) {
            System.out.println(ioe.toString());
        }
    }

}
