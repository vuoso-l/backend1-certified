package ejercicio;

import org.apache.log4j.Logger;

public class Lion {
    private String name;
    private int age;

    private static final Logger logger = Logger.getLogger(Lion.class);

    public Lion(String name, int age) {
        this.name = name;
        this.age = age;
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

    public void run(){
        logger.info("The lion " + name + " is running.");
    }

    public void isOldestThanTen() throws Exception {

            if (age > 10) {
                logger.info("The lion " + name + " is " + age + " years old.");
            } else if (age >= 0 && age < 10){
                logger.info("The lion " + name + " is " + age + " years old.");
            } else {
                throw new Exception("The age of the lion " + name + " isn´t correct");
        }
    }

}
