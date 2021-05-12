package clientServer;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args)
    {
        byte bKbdInput[] = new byte[256];
        ServerSocket ss;
        Socket s;
        InputStream is;
        OutputStream os;
        try
        {
            System.out.println(
                    "Socket Server Application");
        }
        catch(Exception ioe)
        {
            System.out.println(ioe.toString());
        }
        try
        {
            ss = new ServerSocket(9999);
            s = ss.accept();
            is = s.getInputStream();
            os = s.getOutputStream();
            byte buf[] = new byte[512];
            int lenght;
            while(true)
            {
                lenght = is.read(buf);
                if(lenght == -1)
                    break;
                String str = new String(buf, 0);
                StringTokenizer st;
                st   = new StringTokenizer(
                        str, "\r\n");
                str = new String(
                        (String)st.nextElement());
                System.out.println(">  " + str);
                os.write(buf, 0, lenght);
                os.flush();
            }
            is.close();
            os.close();
            s.close();
            ss.close();
        }
        catch(Exception ioe)
        {
            System.out.println(ioe.toString());
        }
        try
        {
            System.out.println("Press Enter to terminate application...");
            System.in.read(bKbdInput);
        }
        catch(Exception ioe)
        {
            System.out.println(ioe.toString());
        }
    }

}
