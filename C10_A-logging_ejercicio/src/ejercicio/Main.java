package ejercicio;

import org.apache.log4j.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Lion.class);
    public static void main(String[] args) throws Exception {
        Lion lion1 = new Lion("Lion 1", -2);
        Tiger tiger1 = new Tiger("Tiger 1", 12, false);

        try {
            lion1.run();
            lion1.isOldestThanTen();
            tiger1.run();
            tiger1.isOldestThanTen();
        }
        catch (Exception e){
            logger.error(e);
        }
    }
}
