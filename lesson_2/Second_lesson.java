package lesson_2;

public class Second_lesson {

/**
     1.  Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
     При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
     2.  Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
     Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
     должно быть брошено исключение MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
     3.  В методе main() вызвать полученный метод, обработать возможные исключения MyArraySizeException и
     MyArrayDataException и вывести результат расчета.
     */
    public static void main(String[] args){

        String[][] arr1 = new String[][]{{"5", "6", "7", "8"}, {"1", "2", "3", "4"}, {"4", "3", "2", "1"}, {"8", "7", "6", "5"}};

        String[][] arr2 = new String[][]{{"5", "6", "7", "8"}, {"1", "2", "3", "4"}, {"4", "3", "2"}, {"8", "7", "6", "5"}};

        // int exampl1 = convertStringToInt("5");
        // System.out.println(exampl1);

        // int exampl2 = convertStringToInt("n");
        // System.out.println(exampl2);
        boolean checkArrSize = checkArrSize(arr2);
        System.out.println(checkArrSize);

        int[][] newArr = convertArrStringToInt(arr1);
        printArrArr(newArr);
        int sum = summArrArr(newArr);
        System.out.println(sum);
    }
    
    public static int convertStringToInt(String text) {
        int nam = Integer.parseInt(text);
        return nam;
    }

    public static int[][] convertArrStringToInt(String[][] arrStr) {
        int[][] arrInt = new int[arrStr.length][arrStr[0].length];
        for (int i = 0; i < arrStr.length; i++) {
            for (int j = 0; j < arrStr[0].length; j++) {
                arrInt[i][j] = convertStringToInt(arrStr[i][j]);
            }
        }
        return arrInt;
    }

    public static boolean checkArrSize(String[][] arrStr) {
        boolean check = true;
        int arrSize = arrStr.length;
        for (int i = 0; i < arrStr.length; i++) {
            //
            if (arrStr[i].length != arrSize) {
                System.out.println(arrStr[i].length);
                check = false;
            }
        }
        return check;
    }

    public static int summArrArr(int[][] arrInt) {
        int sum = 0;
        for (int i = 0; i < arrInt.length; i++) {
            for (int j = 0; j < arrInt[0].length; j++) {
                sum = sum + arrInt[i][j];
            }
        }
        return sum;
    }

    public static void printArrArr(int[][] arrInt) {
        for (int i = 0; i < arrInt.length; i++) {
            for (int j = 0; j < arrInt[0].length; j++) {
                System.out.printf("%d ",arrInt[i][j]);
            }
            System.out.println();
        }
    }
}

abstract class MyException extends Exception{

    private final int x;
    private final int y;

    public MyException(String message, int x, int y){
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