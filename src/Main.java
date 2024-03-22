import java.io.IOException;
import java.util.*;

class Main
{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(calc(str));
    }

    public static String calc(String input) throws IOException {
        String[] array_all_correct_elements = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "/", "*", "-", "+", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] array_split = input.split(" ");
        String result_END = "";
        int[] array_int_element = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int x = 0, y = 0, arab_element = 0, rome_element = 0, result;

        for (int i = 0; i < array_split.length; i++) {                                                                  //Цикл for (ваш КЭП)
            for (int j = 0; j < array_all_correct_elements.length; j++) {                                               //Цикл в цикле (ваш КЭП: часть 2)
                if(j <= 10 && array_split[i].equals(array_all_correct_elements[j])){                                    //Сравнение с массивом на принадлежность к арабской системе
                    arab_element++;
                    if (i == 0){
                        x = array_int_element[j];
                    }else if (i == 2){
                        y = array_int_element[j];
                    }
                }else if(j >= 15 && array_split[i].equals(array_all_correct_elements[j])) {                             //Сравнение с массивом на принадлежность к римской системе
                    rome_element++;
                    if (i == 0){
                        x = array_int_element[j-14];
                    }else if (i == 2){
                        y = array_int_element[j-14];
                    }
                }
            }
        }
                                                                                                                        //Выброс исключений:
        if (array_split.length < 3) {                                                                                   //Мало символов
            throw new IOException("Строка не является математической операцией.");
        } else if (array_split.length > 3) {                                                                            //Много символов
            throw new IOException("Формат математической операции не удовлетворяет заданию.");
        } else if ((arab_element < 2 && rome_element == 0) || (arab_element == 0 && rome_element < 2)) {                //Некорректные символы
            throw new IOException("Введены некорректные данные.");
        } else if (arab_element == 1 && rome_element == 1) {                      //Разные системы
            throw new IOException("Используются одновременно разные системы счисления.");
        } else if (array_split[1].equals("/") && y == 0) {                                                              //Деление на 0
            throw new IOException("Пифагор и Фридрих Гаусс уже выехали за тобой!");
        }

        result = switch (array_split[1]) {                                                                              //Проверка математического действия (/,*,-,+)
            case "/" -> (x / y);
            case "*" -> (x * y);
            case "-" -> (x - y);
            case "+" -> (x + y);
            default -> throw new IOException("Строка не является математической операцией.");                           //Исключение при несоответствии математическим операциям
        };

        if (rome_element == 2 && result <= 0){                                                                          //Исключение в римской системе (отрицательные числа или 0)
            throw new IOException("В римской системе нет отрицательных чисел и нуля.");
        } else if (arab_element == 2) {
            result_END = Integer.toString(result);
        } else if (rome_element == 2) {
            if(result == 100){                                                                                          //Сотни
                result_END = "C";
            }
            if (((result % 100) / 10) != 0) {                                                                           //Десятки
                result_END = array_all_correct_elements[((result % 100) / 10)+23];
            }
            if ((result % 10) != 0) {                                                                                   //Единицы
                result_END += array_all_correct_elements[(result % 10)+14];
            }
        }
        return result_END;
    }
}