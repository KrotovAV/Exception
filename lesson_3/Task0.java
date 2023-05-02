package lesson_3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Task0 {

    /**
     *     1.  Создайте класс исключения, который будет выбрасываться при делении на
     *         0. Исключение должно отображать понятное для пользователя сообщение
     *         об ошибке.
     *     2.  Создайте класс исключений, которое будет возникать при обращении к
     *         пустому элементу в массиве ссылочного типа. Исключение должно
     *         отображать понятное для пользователя сообщение об ошибке
     *     3.  Создайте класс исключения, которое будет возникать при попытке открыть
     *         несуществующий файл. Исключение должно отображать понятное для
     *         пользователя сообщение об ошибке.
     *
     *     4.* Продемонстрировать работу каждого исключения.
     */


    public static void main(String[] args) {

        try {
            test1();
        }
        catch (DivisionByZeroException e){
            System.out.println(e.getMessage());
        }

        try {
            test2(new String[]{"Станислав", "Иван", "Мария", null, "Александр"}, 3);
        }
        catch (NullElementArrayException e){
            System.out.printf("%s\nИндекс элемента: %d\n", e.getMessage(), e.getIndex());
        }

        try
        {
            test3("C:\\test\\test2.txt");
        }
        catch (MyFileNotFoundException e){
            System.out.printf("%s\nНаименование файла: %s\n", e.getMessage(), e.getFileName());
        }

    }

    /**
     * Тестирование работы исключения DivisionByZeroZeroException
     */
    static void test1(){
        try {
            int a = 10 / 0;
        }
        catch (ArithmeticException e){
            throw new DivisionByZeroException("Ошибка деления числа на 0.");
        }
    }

    static void test2(String[] arr, int index){
        try {
            if (arr[index] == null) {
                throw new NullElementArrayException("Элемент массива не проинициализирован.", index);
            }
            System.out.printf("Значение элемента массива: %s\n", arr[index]);
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
    }

    static void test3(String fileName) throws MyFileNotFoundException{

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
            char[] buf = new char[256];
            int count;
            while ((count = fileReader.read(buf)) > 0){
                if (count < 256)
                    buf = Arrays.copyOf(buf,count);

                for (int i = 0; i < buf.length; i++){
                    System.out.print(buf[i]);
                }
            }
            //fileReader.close();
        }
        catch (FileNotFoundException e){
            throw new MyFileNotFoundException("Файл не найден.", fileName);
        }
        catch (IOException e){
            System.out.println("Ошибка чтения файла.");
            /*try {
                fileReader.close();
            }
            catch (IOException ex){
                System.out.println(ex.getMessage());
            }*/
        }
        finally {
            try {
                if (fileReader != null)
                    fileReader.close();
            }
            catch (IOException ex){
                System.out.println(ex.getMessage());
            }
        }

    }

}

/**
 * Исключение: деление на 0
 */
class DivisionByZeroException extends ArithmeticException{

    public DivisionByZeroException(String s) {
        super(s);
    }
}

/**
 * Исключение: обращение к пустому элементу массива
 */
class NullElementArrayException extends RuntimeException{

    private final int index;

    public int getIndex() {
        return index;
    }

    public NullElementArrayException(String message, int index) {
        super(message);
        this.index = index;
    }
}

/**
 * Исключение: файл не найден
 */
class MyFileNotFoundException extends  Exception{

    private final String fileName;

    public String getFileName() {
        return fileName;
    }

    public MyFileNotFoundException(String message, String fileName) {
        super(message);
        this.fileName = fileName;
    }

}


