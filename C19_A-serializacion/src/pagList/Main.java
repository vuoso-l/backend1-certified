package pagList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        WebPage web1 = new WebPage("www.hola.com", "Hola");
        WebPage web2 = new WebPage("www.chau.com", "Chau");
        WebPage web3 = new WebPage("www.hello.com", "Hello");
        WebPage web4 = new WebPage("www.bye.com", "Bye");

        List<WebPage> favoriteWebPages = new ArrayList<WebPage>();

        favoriteWebPages.add(web1);
        favoriteWebPages.add(web2);
        favoriteWebPages.add(web3);
        favoriteWebPages.add(web4);

        try {

            FileOutputStream fos = new FileOutputStream("favoriteWebPages.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(favoriteWebPages);
            oos.close();

            FileInputStream fis = new FileInputStream("favoriteWebPages.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            ois.readObject();
            System.out.println(favoriteWebPages);
            ois.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
