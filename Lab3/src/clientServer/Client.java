package clientServer;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Client {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println(
                "Приложение-клиент\n" +
                        "Введите текст или 'quit' чтобы выйти...");

        try {
            Socket socket = new Socket("localhost", 7777);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            int length;
            while (true) {
                byte[] bKbdInput, buf = new byte[512];
                String text, editedText;

                /*Чтение текста из файла или консоли*/
                text = readText();
                if (text != null) {
                    bKbdInput = text.getBytes(StandardCharsets.UTF_8);
                    length = bKbdInput.length;
                    System.out.println("\n>  " + text);

                    /*Отправка на сервер*/
                    outputStream.write(bKbdInput, 0, length);
                    outputStream.flush();

                    /*Чтение изменений*/
                    length = inputStream.read(buf);
                    if (length == -1)
                        break;
                    editedText = new String(buf);
                    System.out.println(">> " + editedText);

                    if (text.equals("quit"))
                        break;

                    if (writeTextToFile(editedText))
                        System.out.println("Поздравляю! Текст успешно записан в файл:)");
                    else
                        System.out.println("Не вышло:( Текст не записался в файл");
                }
            }
            inputStream.close();
            outputStream.close();
            socket.close();

            System.out.println("Нажмите <Enter> чтобы закрыть приложение...");
            System.in.read();

        } catch (Exception ioe) {
            System.out.println(ioe.toString());
        }
    }

    public static String readText() {
        int inputType;
        String text = null;
        System.out.println("Выберите тип ввода (1 - консольный, 2 - файл):");
        if (in.hasNextInt())
            inputType = in.nextInt();
        else
            inputType = -1;

        /*Чтение текста*/
        if (inputType == 1)
            text = readTextFromConsole();
        if (inputType == 2)
            text = readTextFromFile();
        if (inputType < 1 || inputType > 2) {
            System.out.println("Ошибка! Некорректное значение! По умолчанию будет выбран консольный ввод.");
            text = readTextFromConsole();
        }
        return text;
    }

    public static String readTextFromFile() {
        String pathToFile = "InputFile.txt";
        String enc = "utf-8";
        LinkedList<String> text = new LinkedList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile), enc));
            String str;
            while (!(str = bufferedReader.readLine()).equals(null)) {
                text.add(str);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        } catch (NullPointerException ignored) {
        }
        return String.join(System.lineSeparator(), text);
    }

    public static String readTextFromConsole() {
        System.out.print("Введите количество строк: ");
        int n;
        if (in.hasNextInt()) {
            n = in.nextInt();
        } else
            n = -1;
        LinkedList<String> text = new LinkedList<>();
        if (n > 0) {
            System.out.println("Введите текст:");
            in.nextLine();
            for (int i = 0; i < n; i++) {
                String str = in.nextLine();
                text.add(str);
            }

        } else {
            System.out.println("Ошибка ввода! Приложение будет закрыто.");
            text.add("quit");
        }

        if (isEmptyText(text))
            return null;
        return String.join(System.lineSeparator(), text);
    }

    public static boolean writeTextToFile(String text) {
        String pathToFile = "OutputFile.txt";
        String enc = "utf-8";
        try {
            BufferedWriter out;
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathToFile), enc));
            out.write(text);
            out.close();
        } catch (IOException e) {
            System.out.println(e.toString());
            return false;
        }
        return true;
    }

    private static boolean isEmptyText(LinkedList<String> text) {
        boolean isEmpty = true;
        for (String s : text) {
            isEmpty = s.isEmpty();
            if (!isEmpty)
                break;
        }
        return isEmpty;
    }
}

