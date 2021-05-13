package clientServer;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

///TODO Доработать бработку текста

public class Server {

    public static void main(String[] args) {
        System.out.println("Приложение-сервер");
        try {
            ServerSocket serverSocket = new ServerSocket(7777);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            int length;
            while (true) {
                /*Чтение текста от клиента*/
                byte[] bufIn = new byte[512], bufOut;
                length = inputStream.read(bufIn);
                if (length == -1)
                    break;
                String text = new String(bufIn, 0, bufIn.length);
                System.out.println(">  " + text);

                /*Отправка измененного текста*/
                String str = editText(text);
                System.out.println("Text:");
                System.out.println(str);
                bufOut = str.getBytes(StandardCharsets.UTF_8);
                outputStream.write(bufOut, 0, bufOut.length);
                outputStream.flush();
            }
            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        } catch (Exception ioe) {
            System.out.println(ioe.toString());
        }
        try {
            System.out.println("Нажмите <Enter> чтобы закрыть приложение...");
            System.in.read();
        } catch (Exception ioe) {
            System.out.println(ioe.toString());
        }
    }

    public static String editText(String text) {
        StringBuilder editedText = new StringBuilder();
        StringTokenizer lines = new StringTokenizer(text, "\r\n");    // Делим текст на строчки

        while (lines.hasMoreElements()) {
            String line = (String) lines.nextElement();
            System.out.println("Line = " + line);
            StringTokenizer words = new StringTokenizer(line, " ");  // делим строчку на слова
            boolean isFirst = true;                                         // проверка на первое слово (это чтобы не было лишних пробелов)
            while (words.hasMoreElements()) {
                String word = words.nextToken();
                System.out.println("Word = " + word);
                if (isHex(word)) {                                          // если слово - шеснадцетиричное число
                    word.replaceAll(" ", "");
                    word = String.valueOf(
                            Integer.parseInt(word.substring(2), 16)); // переводим его в десятичное число
                    System.out.println("EditedWord = " + word);
                }
                                                                            //Если нет, то ничего с ним не делаем
                if (!isFirst)
                    editedText.append(" ");                                 // если слово не первое, то сначала добавляем пробел
                else
                    isFirst = false;                                        // если слово первое, то пробел сначала добавлять не нужно, просто отмечаем, что остальные слова уже не первые
                editedText.append(word);                                    //записываем слово
            }
            editedText.append("\n");                                        // добавляем в текст перенос строки
        }
        return editedText.toString();
    }

    private static boolean isHex(String word) {
        String hexLetters = "abcdfABCDF", numbers = "0123456789 ";
        if (!word.startsWith("0x")) {
            System.out.println("NotHex-1");
            return false; }
        for (int i = 2; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!(hexLetters.indexOf(ch) != -1 || numbers.indexOf(ch) != -1)){
                System.out.println("NotHex-2");
                return false;}
        }
        return true;
    }
}