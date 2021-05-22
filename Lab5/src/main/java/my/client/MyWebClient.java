package main.java.my.client;


import main.java.my.service.Measures;
import main.java.my.service.OldRussianConverter;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class MyWebClient {
    public static void main(String[] args) throws MalformedURLException {
        Scanner scanner = new Scanner(System.in);
        // создаем ссылку на wsdl описание
        URL url = new URL("http://localhost:8080/ws/hello?wsdl");

        // Параметры следующего конструктора смотрим в самом первом теге WSDL описания - definitions
        // 1-ый аргумент смотрим в атрибуте targetNamespace
        // 2-ой аргумент смотрим в атрибуте name
        QName qname = new QName("http://service.my.java.main/", "OldRussianConverterImplService");

        // Теперь мы можем дотянуться до тега service в wsdl описании,
        Service service = Service.create(url, qname);
        // а далее и до вложенного в него тега port, чтобы
        // получить ссылку на удаленный от нас объект веб-сервиса
        OldRussianConverter hello = service.getPort(OldRussianConverter.class);
        // Ура! Теперь можно вызывать удаленный метод
        while (true) {
            System.out.println("Выберите исходную меру:");
            Measures from = inputMeasureType(scanner);
            if (from != null) {
                System.out.println("Выберите конечную меру:");
                Measures to = inputMeasureType(scanner);
                if (to != null) {
                    System.out.println("Введите число:");
                    double num;
                    if (scanner.hasNextDouble()) {
                        num = scanner.nextDouble();
                        System.out.print("Результат: ");
                        System.out.println(hello.convert(num, from, to));
                    } else
                        System.out.println("Ошибка ввода! Нужно ввести число");
                }
            }
            System.out.println("Хотите выйти? (y/n)");
            scanner.nextLine();
            String exit = scanner.nextLine();
            if(exit.equals("y"))
                break;
        }
    }

    private static Measures inputMeasureType(Scanner scanner) {
        System.out.println("1 - Точка");
        System.out.println("2 - Линия");
        System.out.println("3 - Вершок");
        System.out.println("4 - Пядь");
        System.out.println("5 - Аршин");
        System.out.println("6 - Сажень");
        System.out.println("7 - Верста");
        int type = -1;
        if (scanner.hasNextInt())
            type = scanner.nextInt();
        switch (type) {
            case 0:
                return null;
            case 1:
                return Measures.TOCHKA;
            case 2:
                return Measures.LINIA;
            case 3:
                return Measures.VERSHOK;
            case 4:
                return Measures.PYAD;
            case 5:
                return Measures.ARSHIN;
            case 6:
                return Measures.SAZHEN;
            case 7:
                return Measures.VERSTA;
        }
        System.out.println("Ошибка ввода! Неверное значение");
        return null;
    }


}
