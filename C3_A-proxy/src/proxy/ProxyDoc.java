package proxy;

import java.util.List;

public class ProxyDoc implements IRegistroDoc{
    private DocService docService;
    private List<String> usuarios;

    public ProxyDoc(DocService docService, List<String> usuarios) {
        this.docService = docService;
        this.usuarios = usuarios;
    }

    @Override
    public void registroCon(String url, String email) {
        if(this.usuarios.contains(email))
            this.docService.registroCon(url, email);
        else
            System.out.println("Usuario no registrado");
    }
}
