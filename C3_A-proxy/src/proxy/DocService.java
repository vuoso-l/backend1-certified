package proxy;

import java.util.List;

public class DocService implements IRegistroDoc{

    @Override
    public void registroCon(String url, String email) {
        System.out.println("Registro usuario con email " + email + ", accediendo a " + url);
    }
}
