import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String s = scanner.nextLine();
        String s1 = calc(s);
        System.out.println("Результат:\n" + s1);
    }
    // Калькулятор
    public static String calc(String input) {
        String s2 = new String();
        String s3 = new String();
        String res = new String();
        if (input.contains("X") || input.contains("I") || input.contains("V")) {
            s2 = roman(input);
            res = s2;
        } else {
            s3 = arab(input);
            res = s3;
        }
        return res;
    }
    // Метод вычисления с арабскими числами
    public static String arab(String arab) {
        int sum, raz, mnoj, del,a=0,b=0;
        arab = arab.trim();
        String[] mas = arab.split(" ");
        if (mas.length > 3)
            throw new ArrayIndexOutOfBoundsException("Неверное выражение");
        try {
            a = Integer.parseInt(mas[0]);
            b = Integer.parseInt(mas[2]);

        }catch (NumberFormatException e){
            System.out.println("Введено не целое число");
            System.exit(0);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Неверное выражение");
            System.exit(0);
        }

        if (a < 1 || a > 10)
            throw new IllegalArgumentException("Число должно быть между 1 и 10");
        if (b < 1 || b > 10)
            throw new IllegalArgumentException("Число должно быть между 1 и 10");
        String put = new String();
        switch (mas[1]) {
            case "+":
                sum = a + b;
                put = String.valueOf(sum);
                break;
            case "-":
                raz = a - b;
                put = String.valueOf(raz);
                break;
            case "*":
                mnoj = a * b;
                put = String.valueOf(mnoj);
                break;
            case "/":
                del = a / b;
                put = String.valueOf(del);
                break;
        }
        return put;
    }
    // Метод вычисления с римскими числами
    public static String roman(String roman) {
        int sum, raz, mnoj, del;
        roman = roman.trim();
        String[] mas = roman.split(" ");
        int a = romanToArabic(mas[0]);
        int b = romanToArabic(mas[2]);
        String put = new String();
        int res = 0;

        switch (mas[1]) {
            case "+":
                sum = a + b;
                res = sum;
                break;
            case "-":
                raz = a - b;
                res = raz;
                break;
            case "*":
                mnoj = a * b;
                res = mnoj;
                break;
            case "/":
                del = a / b;
                res = del;
                break;
        }
        put = arabicToRoman(res);

        return put;
    }
    // Метод для преобразования римских чисел в арабские
    public static int romanToArabic(String input) {
        // Создаем отображение (Map) для хранения соответствия римских цифр и их значения в арабской системе
            Map<Character, Integer> romanNumerals = new HashMap<>();
            romanNumerals.put('I', 1);
            romanNumerals.put('V', 5);
            romanNumerals.put('X', 10);
            romanNumerals.put('L', 50);
            romanNumerals.put('C', 100);

            int result = 0;
            int prevValue = 0;

            for (int i = input.length() - 1; i >= 0; i--) {
                try {
                    int curValue = romanNumerals.get(input.charAt(i));

                    if (curValue < prevValue) {
                        result -= curValue;
                    } else {
                        result += curValue;
                    }

                    prevValue = curValue;
                }catch (NullPointerException e){
                    System.out.println("Пользователь ввел араб и рим");
                    System.exit(0);
                }
            }
            if (result > 10){
                System.out.println("Введенное число больше 10");
                System.exit(0);
            }

            return result;

    }
    // Метод для преобразования арабских чисел в римские
    public static String arabicToRoman(int number) {
        if (number <= 1)
            throw new IllegalArgumentException("Число должно быть положительным");

        String[] romanUnits = {"","I","II","III","IV","V","VI","VII","VIII","IX"};
        String[] romanTens = {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC","C"};

        return romanTens[number / 10] + romanUnits[number % 10];
    }
}



