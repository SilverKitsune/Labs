package clientServer;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

///TODO Чтение из файла

public class Client {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Socket socket;
        InputStream inputStream;
        OutputStream outputStream;
        System.out.println(
                "Socket Client Application" +
                        "\nEnter any string or" +
                        " 'quit' to exit...");
        try {
            socket = new Socket("localhost", 7777);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            int length;
            String str;
            while (true) {
                byte[] bKbdInput = new byte[256];
                byte[] buf = new byte[512];
                String text = readText(in);

                bKbdInput = text.getBytes(StandardCharsets.UTF_8);
                length = bKbdInput.length;
                if (length != 1) {

                    /*Отправка на сервер*/
                    System.out.print(">  " + text);
                    outputStream.write(bKbdInput, 0, length);
                    outputStream.flush();

                    /*Чтение изменений*/
                    length = inputStream.read(buf);
                    if (length == -1)
                        break;
                    str = new String(buf, 0, buf.length);
                    /*st = new StringTokenizer(str, "\r\n");
                    str = (String) st.nextElement();*/
                    System.out.println(">> " + str);

                    if (str.equals("quit"))
                        break;
                }
            }
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (Exception ioe) {
            System.out.println(ioe.toString());
        }
        try {
            System.out.println(
                    "Press <Enter> to " + "terminate application...");
            in.nextLine();
            in.nextLine();
        } catch (Exception ioe) {
            System.out.println(ioe.toString());
        }
    }

    public static String readText(Scanner in) {

        System.out.print("Введите количество строк: ");
        int n;
        if (in.hasNextInt()) {
            n = in.nextInt();
        } else {
            n = -1;
            System.out.println("Ошибка ввода");
        }
        LinkedList<String> text = new LinkedList<>();
        if (n > 0) {
            System.out.println("Введите текст:");
            in.nextLine();
            for (int i = 0; i < n; i++)
            {
                String str = in.nextLine();
                //System.out.println("** "+str);
                text.add(str);
            }

        } else
            text.add("quit");
        //System.out.println("All: " + String.join(System.lineSeparator(), text));
        return String.join(System.lineSeparator(), text);
    }

}
