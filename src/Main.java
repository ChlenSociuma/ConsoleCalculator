//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Приветствую.");
        System.out.println("Help - вывести условия корректной работы программы. Stop - завершить программу.");
        Help help = new Help();
        Scanner scan = new Scanner(System.in);
        while (true) {                                                                        // цикл выполнения основной логики программы
                System.out.println("Введите данные согласно строгим условиям:");
                GetInput.forSorting = scan.nextLine();                                        // получаем строку
                String coughtString = GetInput.forSorting;
                if (coughtString.contains("+")) {
                    System.out.println("\"" + StringCalculator.stringPlusN() + "\"");         // выводим в консоль результат операции сложения в калькуляторе
                }
                else if (coughtString.contains("-")) {
                    System.out.println("\"" + StringCalculator.stringMinusN() + "\"");        // вычитания
                }
                else if (coughtString.contains("*")) {
                    System.out.println("\"" + StringCalculator.stringMultiplyN() + "\"");     // умножения
                }
                else if (coughtString.contains("/")) {
                    System.out.println("\"" + StringCalculator.stringDivideN() + "\"");       // деления
                }
                else if (coughtString.equalsIgnoreCase("stop")) {
                    System.out.println("Пока - пока!");                                       // ломаем цикл и завершаем программу если поймано стоп слово
                    break;
                }
                else if (coughtString.equalsIgnoreCase("help")) {
                    help.runHelp();                                                           // выводим в консоль условия
                }
                else if (!coughtString.contains("+") || !coughtString.contains("-") || !coughtString.contains("*") || !coughtString.contains("/")) {                                                        // если не введен оператор
                    System.out.println("Оператор для математического действия не обнаружен! Попытайтесь снова или введите - Help."); // ограничение математических действий
                }
        }
        scan.close();
    }
}

class GetInput {                                                            // механизм получения данных
    static String forSorting;                                               // Переменная со ссылкой на введенную строку

    static String getString() {                                             // получаем первый компонент
        String reversedForSorting = new StringBuilder(forSorting).reverse().toString(); // переворот строки, чтобы поделить по пробелам корректнее
        String[] stringParts = reversedForSorting.split(" ", 3);
        String notReadyString = stringParts[2];
        String reversedReadyString;
        if (notReadyString.startsWith("\"") && notReadyString.endsWith("\"") && notReadyString.length() > 1) {
            reversedReadyString = notReadyString.replaceAll("\"", "");
            String readyString = new StringBuilder(reversedReadyString).reverse().toString();
            return readyString;
        } else {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Нарушено условие для первого элемента... Завершаю работу.");
                System.exit(0);
            }
        }
        return null;
    }
    static String getN() {                                                  // второй компонент
        String reversedForSorting = new StringBuilder(forSorting).reverse().toString(); // переворот строки, чтобы поделить по пробелам корректнее
        String[] stringParts = reversedForSorting.split(" ", 3);
        String readyString;
        if (stringParts.length < 3) {                                       // если нарушен не получилось найти 3 элемента
            try {
                throw new IndexOutOfBoundsException();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Очень смешно... Обиднее нарушения условий я еще не встречал.");
                System.out.println("Завершаю работу. :(");
                System.exit(0);
            }
        } else if (stringParts[0].equals("0")) {
            try {
                throw new ArithmeticException();
            } catch (ArithmeticException e) {
                System.out.println("Нельзя же делить на \"0\", я очень оскорблен!");
                System.out.println("Завершаю работу. :(");
                System.exit(0);
            }
        } else {
            readyString = new StringBuilder(stringParts[0]).reverse().toString(); // переворачиваем обратно

            return readyString;
        }
        return null;
    }
}

class StringCalculator {                                                     // функционал калькулятора

    static String stringPlusN() {                                            // строка + n
        String forSummary = GetInput.getN();
        String clearString = forSummary.replaceAll("\"", "");
        if (!forSummary.startsWith("\"") && !forSummary.endsWith("\"")) {    // пытаемся вызвать исключение
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Нарушены условия для  сложения строк... Оскорблен... Завершаю работу.");
                System.exit(0);
            }
        } else {
            String summaryString = GetInput.getString() + clearString;
            ArrayList<String> moreThenForty = new ArrayList<>();
            String afterSummaryMoreForty;
            String[] stringParts = summaryString.split("");
            if (stringParts.length >= 40) {
                for (int i = 0; i < 41; i++) {                                   // создаем цикл, если вдруг достигнет 40 символ, 41 индекс будет "..."
                    if (i == 40) {
                        moreThenForty.add("...");
                    } else {
                        moreThenForty.add(stringParts[i]);
                    }
                }
                return afterSummaryMoreForty = String.join("", moreThenForty);
            } else {
                return summaryString;                                        // возвращаем сложенную строку, если меньше 40 символов
            }
        }
        return null;
    }
    static String stringMinusN() {                                           // строка - n
        String beforeMinus = GetInput.getN();
        String beforeMinus2 = beforeMinus.replaceAll("\"", "");
        if (!beforeMinus.startsWith("\"") && !beforeMinus.endsWith("\"") ) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Нарушены условия для  вычитания строк... Оскорблен... Завершаю работу.");
                System.exit(0);
            }
        } else if (beforeMinus2.length() > 10 || GetInput.getString().length() > 10) {
            System.out.println("Нарушены условия! Убедитесь, что длинна каждой введенной строки не превышает 10 символов... Оскорблен... Завершаю работу.");
            System.exit(0);
        } else {
            String afterMinus = GetInput.getString().replaceAll(beforeMinus2, "");
            return afterMinus;
        }
        return null;
    }
    static String stringMultiplyN() {                                        // строка * n
        String afterMultiply;
        ArrayList<String> moreThenForty = new ArrayList<>();
        String forException = GetInput.getN();
        String clearInteger = forException.replaceAll("\"", "");
        String afterMultiplyMoreForty;
        afterMultiply = GetInput.getString().repeat(Integer.parseInt(clearInteger));
        String[] stringParts = afterMultiply.split("");
        boolean check = Character.isDigit(clearInteger.charAt(0));
        if (forException.startsWith("\"") || forException.endsWith("\"")) {  // провоцируем исключение заранее и обрабатываем
            try {
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Оскорблен... Нельзя умножать на что-то кроме целого числа... Завершаю работу!");
                System.exit(0);
            }
        } else if (!check) {                                                 // ловим исключение, если символ не число
            try {
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Оскорблен... Нельзя умножать на что-то кроме целого числа... Завершаю работу!");
                System.exit(0);
            }
        } else if (Integer.parseInt(clearInteger) > 10) {                    // ловим исключение, если число больше 10
            try {
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Не может быть множитель больше 10... Завершаю работу!");
                System.exit(0);
            }
        } else {
                if (stringParts.length >= 40) {
                    for (int i = 0; i < 41; i++) {                           // еще проверка на >40
                        if (i == 40) {
                            moreThenForty.add("...");
                        } else {
                            moreThenForty.add(stringParts[i]);
                        }
                    }
                    return afterMultiplyMoreForty = String.join("", moreThenForty); // Возвращаем строку, после обрезания
                } else {
                    return afterMultiply;                                    // возвращаем сложенную строку, если меньше 40 символов
                }
            } return null;
    }
    static String stringDivideN() {                                          // строка / n
        String[] beforeDivide = GetInput.getString().split("");        // массив для укорачивания
        String forException = GetInput.getN();
        String clearInteger = forException.replaceAll("\"", "");
        ArrayList<String> toBuild = new ArrayList<>();
        String afterDivide;                                                  // переменная для возврата строки
        boolean check = Character.isDigit(clearInteger.charAt(0));
        if (forException.startsWith("\"") || forException.endsWith("\"")) {  // провоцируем исключение заранее и обрабатываем
            try {
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Оскорблен... Нельзя делить на что-то кроме целого числа... Завершаю работу!");
                System.exit(0);
            }
        } else if(!check) {
            try {
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Оскорблен... Нельзя делить на что-то кроме целого числа... Завершаю работу!");
                System.exit(0);
            }
        } else if (Integer.parseInt(clearInteger) > 10) {                    // ловим исключение, если число больше 10
            try {
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Не может быть делитель больше 10... Завершаю работу!");
                System.exit(0);
            }
        } else {
            int toMakeCutString = beforeDivide.length / Integer.parseInt(clearInteger); // определяем, сколько останется символов в строке после деления
            if (toMakeCutString <= 0) {                                      // на случай, если длинна строки после деления меньше 0
                toMakeCutString = 1;
                for (int i = 0; i < toMakeCutString; i++) {
                    toBuild.add(beforeDivide[i]);
                }
            } else {
                for (int i = 0; i < toMakeCutString; i++) {
                    toBuild.add(beforeDivide[i]);                            // кидаем в коллекцию диапазон символов после укорачивания
                }
            }
            }
            afterDivide = String.join("", toBuild);                  // собираем строку
            return afterDivide;
        }
}

class Help {
    void runHelp() {
        System.out.println("1 - Введите строку в которой содержатся три ключевых элемента для вычисления, строго через пробел. Образец: \"abc\" + \"abc\" или \"abc\" * n.");
        System.out.println("1.1 - Первым элементом может быть только подстрока из символов в формате \"abc\". Убедитесь, что элемент заключен в кавычки!");
        System.out.println("1.2 - Вторым значением может быть любой из четырех математических операторов (\"+\"; \"-\"; \"*\"; \"/\") без кавычек");
        System.out.println("1.3 - Третьим значением может быть как подстрока в формате \"abc\" для сложения или вычитания, так и целым числом для деления или умножения, но без кавычек");
        System.out.println("2 - Основные возможности программы.");
        System.out.println("2.1 - Результатом сложения двух строк, является строка состоящая из переданных.");
        System.out.println("2.2 - Результатом деления строки на число n, является строка в n раз короче исходной.");
        System.out.println("2.3 - Результатом умножения строки на число n, является строка, в которой переданная строка повторяется ровно n раз.");
        System.out.println("2.3 - Результатом вычитания строки из строки, является строка, в которой удалена переданная подстрока или сама исходная строка, если в нее нет вхождения вычитаемой строки.");
        System.out.println("3 - Ограничения.");
        System.out.println("3.1 - Калькулятор может принимать на вход числа от 1 до 10 включительно, не более. А строки длинной не более 10 символов.");
        System.out.println("3.2 - Если строка, полученная в результате работы программы длиннее 40 символов, то в выводе после 40 символа будут стоять три точки (...).");
        System.out.println("3.3 - Программа умеет работать только с целыми числами.");
        System.out.println();
        System.out.println("ВАЖНО!!! - В случае нарушения какого-либо пункта, программа будет оскорблена вашим безразличием к призыву ознакомиться с основными условиями и завершит свою работу.");
        System.out.println();
    }
}

