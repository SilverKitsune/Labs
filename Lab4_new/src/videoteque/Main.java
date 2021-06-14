package videoteque;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.bind.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(VideoLibrary.class);
            while (true) {
                System.out.println("Что вы хотите сделать?");
                System.out.println("Если хотите считать данные из XML-файла - нажмите 1");
                System.out.println("Если хотите записать данные в XML-файл - нажмите 2");
                System.out.println("Если хотите выйти - нажмите 3");
                int choice;
                if (scanner.hasNextInt())
                    choice = scanner.nextInt();
                else
                    choice = 3;
                if (choice < 1 || choice > 3) {
                    System.out.println("Ошибка ввода! Приложение будет закрыто.");
                    break;
                }
                if (choice == 1) {
                    Unmarshaller u = context.createUnmarshaller();
                    readXmlFile(u);
                } else if (choice == 2) {
                    Marshaller m = context.createMarshaller();
                    writeToXmlFile(m);
                } else
                    break;

                System.out.println("Чтобы продолжить нажмите <Enter>...");
                System.in.read();
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void readXmlFile(Unmarshaller unmarshaller) {
        File xmlFile = new File("videoLibrary.xml");
        try {
            VideoLibrary videoLibrary = (VideoLibrary) unmarshaller.unmarshal(xmlFile);
            System.out.println(videoLibrary.toString());
            System.out.println("Демаршаллизация прошла успешно!");
        } catch (JAXBException e) {
            System.out.println("Ошибка при демаршаллизации!");
        }
    }

    public static void writeToXmlFile(Marshaller marshaller) {
        try {
            File outFile = new File("newVideoLibrary.xml");
            OutputStream outputStream = new FileOutputStream(outFile);
            VideoLibrary newVideoLibrary = createVideoLibrary();
            marshaller.marshal(newVideoLibrary, outputStream);
            outputStream.close();
            System.out.println("Маршаллизация прошла успешно!");
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода!");
        } catch (JAXBException e) {
            System.out.println("Ошибка при маршеллизации!");
        }
    }

    private static VideoLibrary createVideoLibrary() {
        ObjectFactory objectFactory = new ObjectFactory();
        VideoLibrary videoLibrary = objectFactory.createVideoLibrary();
        VideoLibrary.Movie movie1 = objectFactory.createVideoLibraryMovie(),
                movie2 = objectFactory.createVideoLibraryMovie();
        VideoLibrary.Movie.Director director1 = objectFactory.createVideoLibraryMovieDirector(),
                director2 = objectFactory.createVideoLibraryMovieDirector();
        VideoLibrary.Clients.Client my_client = objectFactory.createVideoLibraryClientsClient();
        my_client.name = "Джон Смит";
        my_client.moviesCount = 3;
        my_client.totalPrice = my_client.moviesCount * 100;
        videoLibrary.clients = objectFactory.createVideoLibraryClients();
        videoLibrary.clients.client = new ArrayList<>();
        videoLibrary.clients.client.add(my_client);
        director1.setFirstName("Джеймс");
        director1.setLastName("Кэмерон");
        movie1.director = new ArrayList<>();
        movie1.director.add(director1);
        movie1.genre = new ArrayList<>();
        movie1.genre.add("фантастика");
        movie1.genre.add("боевик");
        movie1.genre.add("триллер");
        movie1.setRating(8.0f);
        movie1.setTitle("Терминатор");
        movie1.setType("vhs");
        movie1.setYear(1984);

        director2.setFirstName("Кристофер");
        director2.setLastName("Нолан");
        movie2.director = new ArrayList<>();
        movie2.director.add(director2);
        movie2.genre = new ArrayList<>();
        movie2.genre.add("фантастика");
        movie2.genre.add("боевик");
        movie2.genre.add("триллер");
        movie2.setTitle("Довод");
        movie2.setYear(2020);
        movie2.setType("blueRay");
        movie2.setRating(7.6f);
        videoLibrary.movie = new ArrayList<>();
        videoLibrary.movie.add(movie1);
        videoLibrary.movie.add(movie2);
        return videoLibrary;
    }

}
