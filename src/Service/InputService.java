package Service;

import java.util.Date;
import java.util.Scanner;

public class InputService {

    //HANDLE INPUT EXCEPTION
    public static final Scanner SC = new Scanner(System.in);

    //ENTER INTEGER WITH MIN MAX BOUND
    public static int inputInt(String message, int min, int max) { //[0, Max value]
        //[0, 15], Enter value: 
        boolean isValid = false;
        int value = 0;
        while (!isValid) {
            System.out.print(message);
            try {
                value = Integer.parseInt(SC.nextLine());
                if (value >= min && value <= max) {
                    isValid = true;
                } else {
                    System.out.println("Value is out of bound!");
                }
            } catch (Exception e) {
                System.out.println("Value is not valid!");
            }
        }
        return value;
    }

    //ENTER INTEGER
    public static int inputInt(String message) {
        boolean isValid = false;
        int value = 0;
        while (!isValid) {
            System.out.print(message);
            try {
                value = Integer.parseInt(SC.nextLine());
                isValid = true;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return value;
    }

    //ENTER DOUBLE WITH MIN MAX BOUND
    public static double inputDouble(String message, double min, double max) {
        boolean isValid = false;
        double value = 0;
        while (!isValid) {
            System.out.print(message);
            try {
                value = Double.parseDouble(SC.nextLine());
                if (value >= min && value <= max) {
                    isValid = true;
                } else {
                    System.out.println("Value is out of bound!");
                }
            } catch (Exception e) {
                System.out.println("Value is not valid!");
            }
        }
        return value;
    }

    //ENTER NON EMPTRY STRING
    public static String inputString(String message) {
        boolean isValid = false;
        String value = "";
        while (!isValid) {
            System.out.print(message);
            value = SC.nextLine();
            if (!value.isEmpty()) {
                isValid = true;
            } else {
                System.out.println("Value can not be empty!");
            }
        }
        return value;
    }

    public static String inputPattern(String message, String pattern, String patternOnScreen) {
        boolean isValid = false;
        String value = "";
        while (!isValid) {
            System.out.print(message);
            value = SC.nextLine();
            if (value.matches(pattern)) {
                //pattern: form
                isValid = true;
            } else {
                System.out.println("Value must be in format " + patternOnScreen);
            }
        }
        return value;
    }

    public static String inputDate(String message) {
        boolean check = true;
        String date;
        do {
                System.out.println(message + "[d/m/y]: ");
                date = SC.nextLine();
                if (isDate(date)) return date;
                check = !isDate(date);
        } while (check);
        return null;
    }

    public static boolean isDate(String date) {
        boolean check = false;
        try {
            String[] result = date.split("[/-]");
            switch (result[1]) {
                case "1", "3", "5", "7", "8", "10", "12" : 
                    check = Integer.parseInt(result[0]) <= 31;
                break;
                case "4", "6", "9", "11" :
                    check = Integer.parseInt(result[0]) <= 30;
                break;
                case "2" : 
                    if (Integer.parseInt(result[2]) % 4 == 0) {
                        check = Integer.parseInt(result[0]) <= 29;
                    }
                    else check = Integer.parseInt(result[0]) <= 28;
                break;
            }

        } catch (Exception e) {
            System.out.println("Error Input");
        }
        return check;    
    }

    public static boolean inputBoolean(String message) {
        String data = inputPattern(message, "[TtFfYyNn]", "Only T(true) or F(false)");
        if (data.equalsIgnoreCase("T")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNumberic(String input) {
        try {
            double data = Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
