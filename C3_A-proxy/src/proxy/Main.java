package proxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> usuarios = new ArrayList<>();
        usuarios.add("aaa@gmail.com");
        usuarios.add("bbb@gmail.com");
        usuarios.add("ccc@gmail.com");
        Doc documento1 = new Doc(1, "www.face.com", "aaaaaaaa", usuarios);
        Doc documento2 = new Doc(2, "www.insta.com", "aaaaaaaa", usuarios);
        List<Doc> docs = Arrays.asList(documento1, documento2);
        IRegistroDoc proxy = new ProxyDoc(new DocService(), usuarios);

        proxy.registroCon("www.face.com", "aaa@gmail.com");
        proxy.registroCon("www.insta.com", "bb@gmail.com");
    }
}
