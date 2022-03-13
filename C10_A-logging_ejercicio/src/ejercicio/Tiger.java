package ejercicio;

import org.apache.log4j.Logger;

public class Tiger {
    private String name;
    private int age;
    private boolean isAlfa;

    private static final Logger logger = Logger.getLogger(Tiger.class);

    public Tiger(String name, int age, boolean isAlfa) {
        this.name = name;
        this.age = age;
        this.isAlfa = isAlfa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAlfa() {
        return isAlfa;
    }

    public void setAlfa(boolean alfa) {
        isAlfa = alfa;
    }

    public void run(){
        logger.info("The tiger " + name + " is running.");
    }

    public void isOldestThanTen(){
        try {
            if (age > 10 && isAlfa) {
                logger.info("The Tiger " + name + " is " + age + " years old and is an alfa tiger.");
            } else if (age > 10 && !isAlfa){
                logger.info("The Tiger " + name + " is " + age + " years old and isn´t an alfa tiger.");
            } else if (age >= 0 && age < 10){
                logger.info("The Tiger " + name + " is " + age + " years old.");
            }
        }
        catch (Exception e){
            logger.error("The age couldn´t be negative", e);
        }
    }

}
