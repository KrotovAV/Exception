package lesson_1;

public class First_HW {
    public static void main(String[] args){
        Task1(new int[]{1, 2, 3}, new int[]{3, 2, 1});
        System.out.println("--------------");
        Task1(new int[]{1, 2, 3, 4}, new int[]{3, 2, 1});
        System.out.println("--------------");
        Task1(null, new int[]{3, 2, 1});
        System.out.println("--------------");

        Task2(new int[]{1, 2, 3}, new int[]{3, 2, 1});
        System.out.println("--------------");
        Task2(new int[]{1, 2, 3}, new int[]{3, 0, 1});
        System.out.println("--------------");
        Task2(new int[]{1, 2, 3, 4}, new int[]{3, 2, 1});
        System.out.println("--------------");
        Task2(null, new int[]{3, 2, 1});
        System.out.println("--------------");
    }


    static void Task1(int[] arr1, int[] arr2) {
        try {
            int[] res = getSubArrays(arr1, arr2);
            printArr(res);
        }
        catch (CustomArraySizeExeption e){
            System.out.println(e.getMessage());
            System.out.printf("length of 1 array: %d\nlength of 2 array: %d\n", e.getLength1(), e.getLength2());
        }
    }

    static void Task2(int[] arr1, int[] arr2) {
        try {
            int[] res = getDevisArrays(arr1, arr2);
            printArr(res);
        }
        catch (CustomArraySizeExeption e){
            System.out.println(e.getMessage());
            System.out.printf("length of 1 array: %d\nlength of 2 array: %d\n", e.getLength1(), e.getLength2());
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    static int[] getSubArrays (int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            if (arr1 == null ) throw new CustomArraySizeExeption("Arrays 1 is not exist", 0, arr2.length);
            else throw new CustomArraySizeExeption("Arrays 2 is not exist", arr1.length, 0 );
        }
        if (arr1.length != arr2.length) {
            throw new CustomArraySizeExeption("length of both arays must be same", arr1.length, arr2.length);
        }
        int[] res = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            res[i] = arr1[i] - arr2[i];
        }
        return res;
    }

    static int[] getDevisArrays (int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            if (arr1 == null ) throw new CustomArraySizeExeption("Arrays 1 is not exist", 0, arr2.length);
            else throw new CustomArraySizeExeption("Arrays 2 is not exist", arr1.length, 0 );
        }
        if (arr1.length != arr2.length) {
            throw new CustomArraySizeExeption("length of both arays must be same", arr1.length, arr2.length);
        }
        if (checkNull(arr2) == false) {
            throw new RuntimeException("Second array contain null element");
        }

        int[] res = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            res[i] = arr1[i] / arr2[i];
        }
        return res;
    }

    public static boolean checkNull(int[] arr){
        boolean check = true;
        for (int e : arr) {
            if (e == 0) check = false;
        }
        return check;
    }

    public static void printArr(int[] arr){
        for (int e : arr) {
            System.out.printf("%d\t", e);
        }
        System.out.println();
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
    public CustomArraySizeExeption(String message) {
        super(message);
    }

    public CustomArraySizeExeption(String message, int lenght1, int lenght2) {
        super(message);
        this.length1 = lenght1;
        this.length2 = lenght2;
    }
}