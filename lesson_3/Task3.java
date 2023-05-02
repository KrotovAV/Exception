package lesson_3;

import java.util.Random;

public class Task3 {

    public static void main(String[] args) {

        Random random = new Random();
        Animal cat1 = new Cat("Персик");
        Animal cat2 = new Cat("Барсик");

        Animal[] animals = new Animal[]{cat1, cat2};

        for (int i = 0; i < animals.length; i++) {
            for (int j = 0; j < 10; j++) {
                int k = random.nextInt(2); // 0 - 1
                try {
                    switch (k) {
                        case 0 -> {
                            animals[i].swim(j * 10);
                            System.out.printf("Животное проплыло %d метров.\n", j * 10);
                        }
                        case 1 -> {
                            animals[i].run(j * 10);
                            System.out.printf("Животное пробежало %d метров.\n", j * 10);
                        }
                    }
                } catch (AnimalSwimException e) {
                    System.out.printf("Ошибка при попытке %s проплыть %d метров. (%s)\n", e.getName(), e.getDistance(), e.getMessage());
                } catch (AnimalRunException e) {
                    System.out.printf("Ошибка при попытке %s пробежать %d метров. (%s)\n", e.getName(), e.getDistance(), e.getMessage());
                }
            }
        }
    }


}

abstract class Animal {

   protected String name;

    public String getName() {
        return name;
    }

    public Animal(String name) {
        this.name = name;
    }

    abstract void swim(int distance) throws AnimalSwimException;

    abstract void run(int distance) throws AnimalRunException;

}

class Cat extends Animal {


    public Cat(String name) {
        super(name);
    }

    @Override
    void swim(int distance) throws AnimalSwimException {
        throw new AnimalSwimException("Кошка не умеет плавать.", name, distance);
    }

    @Override
    void run(int distance) throws AnimalRunException {
        if (distance > 50) throw new AnimalRunException("Кошке тяжело далеко бегать.", name, distance);

        //TODO: Котик бегает ...

    }
}

abstract class AnimalException extends Exception {

    private final String name;
    private final int distance;

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    public AnimalException(String message, String name, int distance) {
        super(message);
        this.name = name;
        this.distance = distance;
    }
}

class AnimalRunException extends AnimalException {

    public AnimalRunException(String message, String name, int distance) {
        super(message, name, distance);
    }
}

class AnimalSwimException extends AnimalException {

    public AnimalSwimException(String message, String name, int distance) {
        super(message, name, distance);
    }
}












