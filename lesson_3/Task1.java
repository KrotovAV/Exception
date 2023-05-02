package lesson_3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Task1 {

    public static void main(String[] args) {
        // try-with-resources
        try
        {
            testNew("C:\\test\\test.txt");
        }
        catch (MyFileNotFoundException e){
            System.out.printf("%s\nНаименование файла: %s\n", e.getMessage(), e.getFileName());
        }

    }

    static void testNew(String fileName) throws MyFileNotFoundException{

        //FileReader fileReader = null;
        try (FileReader fileReader = new FileReader(fileName)) {
            //fileReader = new FileReader(fileName);
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
        /*finally {
            try {
                if (fileReader != null)
                    fileReader.close();
            }
            catch (IOException ex){
                System.out.println(ex.getMessage());
            }
        }*/

    }

    static void testOld(String fileName) throws MyFileNotFoundException{

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

class Printer implements AutoCloseable{

    @Override
    public void close() throws Exception {

    }
}
