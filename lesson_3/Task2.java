package lesson_3;


import java.io.IOException;

/**
    Создайте класс Счетчик, у которого есть метод add(), увеличивающий
    значение внутренней int переменной на 1. Сделайте так, чтобы с объектом
    такого типа можно было работать в блоке try-with-resources. Подумайте, что
    должно происходить при закрытии этого ресурса? Напишите метод для
    проверки, закрыт ли ресурс. При попытке вызвать add() у закрытого
    ресурса, должен выброситься IOException.
 */

public class Task2 {


    public static void main(String[] args) {

        try(Counter counter1 = new Counter(22)) {

            System.out.printf("Текущее значение счетчика %d\n", counter1.add());

            counter1.add();
            counter1.add();

            System.out.printf("Текущее значение счетчика %d\n", counter1.getCounter());
            //counter.closeCounter();
        }
        catch (CloseCounterException e){
            System.out.println(e.getMessage());
        }
        catch (CounterException e){

        }
        catch (CounterV2Exception e){

        }

    }

}

class Counter implements AutoCloseable{

    private int counter;
    private boolean isOpen;

    public int getCounter() {
        return counter;
    }


    /**
     *
     * @return
     * @throws CloseCounterException исключение 1
     * @throws CounterException исключение 2
     * @throws CounterV2Exception исключение 3
     */
    public int add() throws CloseCounterException, CounterException, CounterV2Exception{
        if (!isOpen){
            throw new CloseCounterException("Счетчик закрыт.");
        }
        return ++counter;
    }

    public Counter(){
        isOpen = true;
    }

    public Counter(int counter){
        this.counter = counter;
        isOpen = true;
    }

    public void closeCounter(){
        isOpen = false;
    }

    @Override
    public void close() {
        closeCounter();
    }
}

class CloseCounterException extends IOException{
    public CloseCounterException(String message) {
        super(message);
    }
}

class CounterException extends Exception{
    public CounterException(String message) {
        super(message);
    }
}

class CounterV2Exception extends RuntimeException{
    public CounterV2Exception(String message) {
        super(message);
    }
}