package Exception;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class first_lesson {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    public static void main(String[] args){

        // System.out.println(Task1(null));
        // System.out.println(Task1(new int[] {2, 3, 7, 15}));

        // Task2();
        // Task3();
        // Task3V2();
        Task4();
    }

    static int Task1(int[] arr) {
        if (arr == null) {
            return -2;
        }
        if (arr.length == 0) {
            return -1;
        }
        return arr.length;
    }

    static void Task2() {
        
        while(true) {
            System.out.println("Enter namber to find ");
            if (scanner.hasNextInt()){
                int searh = scanner.nextInt();
                int[] array = new int[random.nextInt(7) + 1];
                if (random.nextInt(2) == 0) {
                    System.out.println("random arraay null");
                    array = null;
                }
                if (array != null) {
                    System.out.println("Start array: ");
                    for (int i = 0; i <array.length; i ++){
                        array[i] = random.nextInt(10);
                        System.out.printf("%d\t", array[i]);
                    }
                    System.out.println();
                }

                int codeResult = procArr(array, searh);
                switch (codeResult) {
                    case -1 -> System.out.println("lenght of array less then 3");
                    case -2 -> System.out.println("elemeny is not exist in array");
                    case -3 -> System.out.println("array is not enicialized");
                    default -> { 
                        System.out.println("array is processed end done");
                        System.out.printf("Element find in index %d .\n", codeResult);
                        return;
                    }

                }
            } else {
                System.out.println("Entered namber is not Integer! Try egain ");
            scanner.nextLine();
            }
        }
    }

    static int procArr(int[] arr, int search) {
        if (arr == null) 
            return -3;
        if (arr.length < 3)
            return -1;
        Arrays.sort(arr);
        System.out.println("Sort array:  ");
        for (int e: arr) {
            System.out.printf("%d\t", e);
        }
        System.out.println();
        int result = Arrays.binarySearch(arr, search);
        if (result < 0 )
            return -2;
        return result; 

    }

    static void Task3() {
    processArr(generateArrArr());
    }
    
    static void processArr(int[][] arr) {
        int sum = 0;
        for(int i = 0; i < arr.length; i++) {
            if (arr[i].length != arr.length) {
                throw new RuntimeException("array size - false");
            }
        }
        for(int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] != 0 && arr[i][j] != 1) {
                    throw new RuntimeException(String.format("array element [%d][%d] = %d", i, j, arr[i][j]));
                } else {
                    sum = sum + arr[i][j];
                }
            }
        }
        System.out.printf("sum of element of array = %d", sum);
    }

    static int[][] generateArrArr() {
        int arr[][] = new int[random.nextInt(2) + 4][5];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = random.nextInt(2);
                if (random.nextInt(50) == 0)
                    arr[i][j] = 2;
                System.out.printf("%d   ", arr[i][j]);
            }
            System.out.println();
        }
        return arr;
    }

    static void Task3V2() {
        int errCode = processArrV2(generateArrArr());
        switch (errCode) {
            case -1 -> System.out.println("not correct array saiz");
            case -2 -> System.out.println("not correct array element");
            default -> System.out.printf("summ of array elements = %d\n", errCode);
        }
    }

    static int processArrV2(int[][] arr) {
        int sum = 0;
        for(int i = 0; i < arr.length; i++) {
            if (arr[i].length != arr.length) {
                return -1;
            }
        }
        for(int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] != 0 && arr[i][j] != 1) {
                    return -2;
                } else {
                    sum = sum + arr[i][j];
                }
            }
        }
        return sum;
    }

    static void Task4() {
        try {
            int[] res = getSummArrays(new int[]{1, 2, 3, 5}, new int[]{3, 2, 1});
            for (int e : res) {
                System.out.printf("%d\t", e);
            }
            System.out.println();
        }
        catch (CustomArraySizeExeption e){
            System.out.println(e.getMessage());
            System.out.printf("length of 1 array: %d\nlength of 2 array: %d\n", e.getLength1(), e.getLength2());
        }
    }

    static int[] getSummArrays (int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            throw new NullPointerException("Both arrays are exist");
        }
        if (arr1.length != arr2.length) {
            throw new CustomArraySizeExeption("length of both arays must be same", arr1.length, arr2.length);
        }
        int[] res = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            res[i] = arr1[i] + arr2[i];
        }
        return res;
    }
}

class CustomArraySizeExeption extends RuntimeException {
    int length1;
    int length2;

    public void setLength1(int length1) {
        this.length1 = length1;
    }
    public int getLength1() {
        return length1;
    }
    public void setLength2(int length2) {
        this.length2 = length2;
    }
    public int getLength2() {
        return length2;
    }
    
    public CustomArraySizeExeption(String message, int lenght1, int lenght2) {
        super(message);
        this.length1 = lenght1;
        this.length2 = lenght2;
    }
}
