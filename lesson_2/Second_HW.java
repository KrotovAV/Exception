package lesson_2;

import java.util.Scanner;

public class Second_HW {

    public static void main(String[] args) {
        System.out.println();
        Task1();
        Task2();
        Task3();
        Task4();
    }

    // Реализуйте метод, который запрашивает у пользователя ввод дробного числа
    // (типа float),
    // и возвращает введенное значение. Ввод текста вместо числа не должно приводить
    // к падению
    // приложения, вместо этого, необходимо повторно запросить у пользователя ввод
    // данных.
    public static void Task1() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        while (true) {
            String input = scanString(scanner);
            try {
                System.out.println(checkFloat(input));
                return;
            } catch (MyDataException e) {
                System.out.printf("%s (%s).\n", e.getMessage(), input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String scanString(Scanner scanner) {
        System.out.println("Write a namber decimal floating point(float type): ");
        String input = scanner.nextLine();
        return input;
    }

    private static float checkFloat(String input) throws MyDataException {
        try {
            return (float) Float.parseFloat(input);
        } catch (NumberFormatException e) {
            throw new MyDataException("Write a namber is not float type", "cv");
        }
    }

    // Если необходимо, исправьте данный код (// Проврка на длинну массива, //
    // проверка деления на ноль)
    public static void Task2() {
        int[] intArray1 = new int[] { 2, 7, 15, 25, 3, 1, 3, 25 };
        int d1 = 0;

        int[] intArray2 = new int[] { 2, 7, 15, 25, 3, 1, 3, 25, 18 };
        int d2 = 1;

        int[] intArray3 = new int[] { 2, 7, 15, 25, 3, 1, 3, 25, 36 };
        int d3 = 1;

        procArr(intArray1, d1);
        procArr(intArray2, d2);
        procArr(intArray3, d3);
    }

    public static void procArr(int[] intArray, int d) {
        printArr(intArray);
        System.out.printf("d = %d\n", d);
        try {
            double catchedRes1 = arrayDivEigEl(intArray, d);
            System.out.printf("%d / %d = %.0f\n", intArray[8], d, catchedRes1);

        } catch (MyArrSizeException e) {
            System.out.printf("%s %d.\n", e.getMessage(), e.getNum());
        } catch (MyIntZeroException e) {
            System.out.printf("%s %d.\n", e.getMessage(), e.getNum());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Proc done\n");
        }
    }

    private static double arrayDivEigEl(int[] arr, int d) throws MyIntZeroException, MyArrSizeException {
        if (arr.length < 9)
            throw new MyIntZeroException("Array size less then 9: ", arr.length);
        if (d == 0)
            throw new MyIntZeroException("Divider is null - ", d);
        return arr[8] / d;
    }

    public static void printArr(int[] arr) {
        for (int e : arr)
            System.out.printf("%d, ", e);
        System.out.println();
    }

    // Дан следующий код, исправьте его там, где требуется (
    public static void Task3() {
        int a = 90;
        int b = 30;
        ani(a, b);

        b = 0;
        ani(90, b);

        Integer c = null;
        ;
        ani(c, 30);
    }

    public static void ani(Integer a, Integer b) {
        try {
            System.out.println(a / b);
            printSum(a, b);
            int[] abc = { 1, 2 };
            abc[3] = a;
        } catch (ArithmeticException ex) {
            System.out.println("Devision by zero!!!");
        } catch (NullPointerException ex) {
            System.out.println("Null value reference!");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Index of element more than array size!");
        } finally {
            System.out.println("Process done\n");
        }
    }

    public static void printSum(Integer a, Integer b) throws NullPointerException {
        if (a == null || b == null)
            throw new NullPointerException("Null element!");
        System.out.println(a + b);
    }

    // Разработайте программу, которая выбросит Exception, когда пользователь вводит
    // пустую строку.
    // Пользователю должно показаться сообщение, что пустые строки вводить нельзя.

    public static void Task4() {
        ch("");
        ch(null);
        ch("Hello!");
        // ch();
    }

    public static void ch (String input) {
    // public static void ch () {

        // Scanner scanner = new Scanner(System.in);
        // System.out.println();
        // String input = scanScan(scanner);

        try {
            System.out.println(checkStringEmpty(input));
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        } catch (MyDataException ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("Process done\n");
        }
    }

    // private static String scanScan(Scanner scanner) {
    //     System.out.println("Write a string: ");
    //     String input = scanner.nextLine();
    //     return input;
    // }

    private static String checkStringEmpty(String input) throws MyDataException {
        if (input != null) {
            input = input.replaceAll("\\s", "");
            if (input.length() != 0) return input;
            else
                throw new MyDataException("Write string is empty", "Empty");
        } else {
            throw new MyDataException("Write string is Null!", "Null");
        }
    }
}

abstract class MyException extends Exception {
    private String text;

    public MyException(String message, String text) {
        super(message);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

class MyDataException extends Exception {
    private String text;

    public MyDataException(String message, String text) {
        super(message);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

class MyIntZeroException extends Exception {
    private int num;

    public MyIntZeroException(String message, int num) {
        super(message);
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}

class MyArrSizeException extends Exception {
    private int num;

    public MyArrSizeException(String message, int num) {
        super(message);
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
