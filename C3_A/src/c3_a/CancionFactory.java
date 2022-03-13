package c3_a;

import java.util.HashMap;
import java.util.Objects;

public class CancionFactory {
    private static final HashMap<String, Cancion> cancionMap = new HashMap<>();

    /*public Cancion getCancion(final String nombreCancion) {
        Cancion cancion = cancionMap.get(nombreCancion);
        if (Objects.isNull(cancion)) {
            cancion = new Cancion(nombreCancion);
            cancionMap.put(nombreCancion, cancion);
            System.out.println("Creando Objecto de canción con el nombre: " + nombreCancion);
            return cancion;

        }
        System.out.println("Recuperando Objecto de canción con el nombre: " + nombreCancion);
        return cancion;

    }*/

    public Cancion getCancion(String nombreCancion) {
        String clave = "nombre: " + nombreCancion;
        System.out.println(clave);
        if(!cancionMap.containsKey(clave)){
            cancionMap.put(clave, new Cancion(nombreCancion));
            System.out.println("Canción creada");
            return cancionMap.get(clave);
        }
        System.out.println("Recuperando Objecto de canción con el nombre: " + nombreCancion);
        return cancionMap.get(clave);

    }
}
