package lesson_2;

import java.util.Random;
import java.util.Scanner;

public class Program2L {

    static Random random = new Random();


    public static void main(String[] args){

        // try{}catch
        // try{}catch{}finally
        // try{}finally{}

        System.out.println(doProcess());


        /**
         Посмотрите на этот код (main_old). Все ли с ним хорошо? Если нет, то скорректируйте его и
         обоснуйте свое решение.
         */
        //task1();

        for (int i = 0; i < 10; i++){
            System.out.printf("\n*** Итерация %d ***\n", i + 1);
            processArray();
        }


    }

    public static String doProcess(){
        try {
            return "TestString";
        }
        catch (Exception e){
            return "Catch String";
        }
        finally {
            return "Finally message";
        }
    }

    static void taskConsole(){
        try {
            task02();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    static void taskWebUI(){
        try {
            task02();
        }
        catch (Exception e){

        }
    }

    static void taskDesctop(){
        try {
            task02();
        }
        catch (Exception e){

        }
    }


    static void task02() throws Exception{
        task03();
    }

    static void task03() throws Exception{

        task04();
    }

    static void task04() throws Exception {
            int res = random.nextInt(2); // 0 - 1
        if (res == 0){
            throw new Exception("Исключение.");
        }
    }



    public static void task1() {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[10];
        System.out.println("Укажите индекс элемента массива, в который хотите записать значение 1: ");
        int index = scanner.nextInt();
        try {
            arr[index] = 1;
        } catch (Exception e ){
            System.out.println(e.getMessage());
        }
    }

    /**
     1.  Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
     При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
     2.  Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
     Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
     должно быть брошено исключение MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
     3.  В методе main() вызвать полученный метод, обработать возможные исключения MyArraySizeException и
     MyArrayDataException и вывести результат расчета.
     */

    public static void processArray(){

        try {
            System.out.printf("Сумма элементов массива: %d\n", processArrayInternal(generateArray()));
        }
        catch (MyArraySizeException e){
            System.out.printf("%s\nТребовалось поработать с массивом 4x4, получили %dx%d\n",
                    e.getMessage(), e.getX(), e.getY());
        }
        catch (MyArrayDataException e){
            System.out.printf("%s\nэлемент под индексом [%d][%d]\n",
                    e.getMessage(), e.getX(), e.getY());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Завершение обработки массива ...");
        }
    }


    /**
     * Метод генерации двумерного массива строк
     * @return
     */
    public static String[][] generateArray(){
        int add = random.nextInt(2);
        String[][] arr = new String[4 + add][4 + add];

        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                if (random.nextInt(51) < 2){
                    arr[i][j] = "abc";
                }
                else{
                    arr[i][j] = Integer.toString(random.nextInt(100));
                }
                System.out.printf("%s ", arr[i][j]);
            }
            System.out.println();
        }
        return arr;
    }



    /**
     * Обработка двумерного массива.
     * @param arr двумерный массив
     * @return сумма элементов массива
     * @throws MyArraySizeException некорректный размер массива
     * @throws MyArrayDataException некорректный формат данных
     */
    private static int processArrayInternal(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        int a = 10 / random.nextInt(5);
        if (arr.length != 4 || arr[0].length != 4)
            throw new MyArraySizeException("Некорректный размер массива.", arr.length, arr[0].length);

        int sum = 0;
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                sum += parseElement(arr[i][j], i, j);
            }
        }
        return sum;
    }

    private static int parseElement(String e, int i, int j) throws MyArrayDataException{
        try {
            return Integer.parseInt(e);
        }
        catch (NumberFormatException ex){
            throw new MyArrayDataException("Некорректный формат данных", i, j);
        }

    }



}

abstract class MyArrException extends Exception{

    private final int x;
    private final int y;

    public MyArrException(String message, int x, int y){
        super(message);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class MyArraySizeException extends MyArrException{

    public MyArraySizeException(String message, int x, int y){
        super(message, x, y);
    }
}

class MyArrayDataException extends MyArrException{

    public MyArrayDataException(String message, int x, int y){
        super(message, x, y);
    }

}

