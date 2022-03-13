package c3_a;

public class Main {
    public static void main(String[] args) {

        CancionFactory cancion = new CancionFactory();
        Cancion cancion1 = cancion.getCancion("Hola");
        cancion1.setArtista("aaaaa");
        cancion1.setGenero("aaaaa");

        Cancion cancion2 = cancion.getCancion("Mundo");
        cancion2.setArtista("bbbbb");
        cancion2.setGenero("bbbbb");

        Cancion cancion3 = cancion.getCancion("Hola");
        cancion3.setArtista("ccccc");
        cancion3.setGenero("ccccc");

        Cancion cancion4 = cancion.getCancion("Mundo");
        cancion4.setArtista("ccccc");
        cancion4.setGenero("ccccc");

        /*final CancionFactory cancion = new CancionFactory();

        Cancion cancion1 = cancion.getCancion("Hola");
        cancion1.setArtista("aaaaa");
        cancion1.setGenero("aaaaa");

        final Cancion cancion2 = cancion.getCancion("Mundo");
        cancion2.setArtista("bbbbb");
        cancion2.setGenero("bbbbb");

        Cancion cancion3 = cancion.getCancion("Hola");
        cancion3.setArtista("ccccc");
        cancion3.setGenero("ccccc");

        final Cancion cancion4 = cancion.getCancion("Mundo");
        cancion4.setArtista("ccccc");
        cancion4.setGenero("ccccc");*/


    }
}
