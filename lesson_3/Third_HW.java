package lesson_3;

import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

// 1. Напишите приложение, которое будет запрашивать у пользователя следующие 
// данные в произвольном порядке, разделенные пробелом:
// Фамилия Имя Отчество датарождения номертелефона пол

// Форматы данных:
// фамилия, имя, отчество - строки
// дата_рождения - строка формата dd.mm.yyyy
// номер_телефона - целое беззнаковое число без форматирования
// пол - символ латиницей f или m.

// 2. Приложение должно проверить введенные данные по количеству. Если количество 
// не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю 
// сообщение, что он ввел меньше и больше данных, чем требуется.

// 3. Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. 
// Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. 
// Можно использовать встроенные типы java и создать свои. 
// Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.

// 4. Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, 
// в него в одну строку должны записаться полученные данные, вида
// <Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
// Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
// Не забудьте закрыть соединение с файлом.
// При возникновении проблемы с чтением-записью в файл, исключение должно быть 
// корректно обработано, пользователь должен увидеть стектрейс ошибки.

public class Third_HW {
    public static void main(String[] args) {
        System.out.println();
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                String writeFio = "Write separated by a space 6 field s: \nLastName FirstName MiddleName DateOfBirth PhoneNumber Gender\n(for example: Petr Ivanovich Nikolaev 06.15.1975 2565898785 m)";
                System.out.println(writeFio);
                String input = scanner.nextLine();
                // System.out.println("Thenks! ");
                MyResult res = parseCheck(scanScan(input));
                // System.out.println(res);
                String write2 = "Write path od derictory ";
                System.out.println(write2);
                String input2 = scanner.nextLine();
                fileWrite(res, input2);
                boolean checF = checkFile(res, input2);
                // System.out.println(checF);
                if (checF == true)
                    return;

            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
            } catch (MyStringException ex) {
                System.out.println(ex.getMessage());
            } catch (MySizeException ex) {
                System.out.printf("%s exzist %d word\n", ex.getMessage(), ex.getNum());
            } catch (DateTimeParseException ex) {
                System.out.println(ex.getMessage());
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } finally {
                System.out.println("Process done\n");
            }
        }

    }

    private static String scanScan(String input) throws MyStringException, MySizeException, ParseException {
        if (input != null) {
            String testInput = input.replaceAll("\\s", "");
            if (testInput.length() != 0) {
                if (input.split(" ").length == 6)
                    return input;
                else {
                    throw new MySizeException("Error! More ore less writes words. Try again!!! ",
                            input.split(" ").length);
                }
            } else
                throw new MyStringException("Error! Write string is empty. Try again!!!", "Empty");
        } else {
            throw new MyStringException("Error! Write string is Null!", "Null");
        }
    }

    private static MyResult parseCheck(String input) throws MyStringException {
        String[] parts = input.split(" ");
        MyResult result = new MyResult(parts[0], parts[1], parts[2], null, 123, null);
        String startData = parts[3];
        String goodData = replaceDate(startData);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date startDate = df.parse(goodData);
            result.setDateOfBirth(startDate);
        } catch (ParseException e) {
            throw new MyStringException("Error! Write string is empty. Try again!!!", "");
        }

        String newPhone = replacePhonNam(parts[4]);
        try {
            int i = Integer.parseInt(newPhone);
            result.setPhoneNumber(i);
        } catch (NumberFormatException nfe) {
            throw new MyStringException("Error! Phone namber is not nambers. Try again!!!", "");
        }
        try {
            String stringFM = checFM(parts[5]);
            if (stringFM != null && (stringFM.contains("M") || stringFM.contains("F"))) {
                result.setGender(stringFM);
            }
        } catch (Exception e) {
            throw new MyStringException("Error! Write in gender M ore F. Try again!!!", "");
        }
        return result;
    }

    static void fileWrite(MyResult res, String dirPath) throws MyStringException, FileNotFoundException {
        dirPath = "D:/GeekBrains/My Git/Exception/lesson_3/";
        String fileName = dirPath + res.getLastName() + ".txt";
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new MyStringException("Error! Derictory not find!", "");
        }

        try (FileWriter fW = new FileWriter(file)) {
            fW.write(res.toString());
        } catch (IOException e) {
            throw new MyStringException("Error! Information nit erite!", "");
        }
    }

    static boolean checkFile(MyResult res, String dirPath) {
        boolean ch = false;
        dirPath = "D:/GeekBrains/My Git/Exception/lesson_3/";
        File f = new File(dirPath + res.getLastName() + ".txt");
        if (f.exists() && !f.isDirectory()) {
            ch = true;
        }
        return ch;
    }

    public static String replaceDate(String in) {
        String[] sumb = new String[] { "[", "=", "-", "/", ",", "]", "!", "(", ")" };
        for (int j = 0; j < sumb.length; j++) {
            if (in.contains(sumb[j])) {
                in = in.replaceAll(sumb[j], ".");
            }
        }
        while (in.contains("..")) {
            in = in.replace("..", ".");
        }
        if (String.valueOf(in.charAt(0)).contains(".")) {
            in = in.substring(1);
        }
        if (String.valueOf(in.charAt(in.length() - 1)).contains(".")) {
            in = in.substring(0, in.length() - 1);
        }
        return in;
    }

    public static String replacePhonNam(String in) {
        String[] sumb = new String[] { "[", "=", "-", "/", ",", "]", "!", "(", ")", ".", ".." };
        for (int j = 0; j < sumb.length; j++) {
            if (in.contains(sumb[j])) {
                in = in.replaceAll(sumb[j], "");
            }
        }
        return in;
    }

    public static String checFM(String in) {
        if (in == null || in.length() != 1)
            return in;
        else {
            if (in.contains("m") || in.contains("м"))
                in = "M";
            if (in.contains("f") || in.contains("F"))
                in = "F";
        }
        return in;
    }

}

class MyResult {
    public String lastName;
    public String firstName;
    public String middleName;
    private Date dateOfBirth;
    public int phoneNumber;
    public String gender;

    public MyResult(String lastName, String firstName, String middleName, Date dateOfBirth, int phoneNumber,
            String gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    String getLastName() {
        return lastName;
    }

    String getFirstName() {
        return firstName;
    }

    String getMiddleName() {
        return middleName;
    }

    Date getDateOfBirth() {
        return dateOfBirth;
    }

    int getPhoneNumber() {
        return phoneNumber;
    }

    String getGender() {
        return gender;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // <Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return "<" + lastName + ">" +
                "<" + firstName + ">" +
                "<" + firstName + ">" +
                "<" + df.format(dateOfBirth) + "> " +
                "<" + phoneNumber + ">" +
                "<" + gender + ">";
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

class MyStringException extends Exception {
    private String text;

    public MyStringException(String message, String text) {
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

class MySizeException extends Exception {
    private int num;

    public MySizeException(String message, int num) {
        super(message);
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}

class MyRuntimeException extends Exception {

    public MyRuntimeException(String message) {
        super(message);
    }
}