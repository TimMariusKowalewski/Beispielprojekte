/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lernerfolgskontrolle20210219_Kowalewski;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kowalewski
 */
public class Aufgabe2
{

    public static void main(String[] args)
    {
        try
        {
            printNumber(0);
            printNumber(9);
            printNumber(87);
            printNumber(33);
            printNumber(20); // bug bei 0en zwischendrin
            printNumber(187);
            printNumber(563);
            printNumber(807); // bug bei 0en zwischendrin
            printNumber(1423);
            printNumber(9999);
            printNumber(23654);
            printNumber(329866);
            printNumber(2323836);
            printNumber(88323846);
            printNumber(491323846);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void printNumber(int Number) throws Exception
    {
        // Setup der Zahlenübersetzungen
        Map<Integer, String[]> number_to_name = new HashMap<>(10);
        String[] single_digits =
        {
            "Null", "Eins", "Zwei", "Drei", "Vier", "Fünf", "Sechs", "Sieben", "Acht", "Neun"
        };
        String[] two_digits =
        {
            "Null", "Zehn", "Zwanzig", "Dreissig", "Vierzig", "Fünfzig", "Sechszig", "Siebzig", "Achtzig", "Neunzig"
        };
        String[] three_digits =
        {
            "Null", "Einhundert", "Zweihundert", "Dreihundert", "Vierhundert", "Fünfhundert", "Sechshundert", "Siebenhundert", "Achthundert", "Neunhundert"
        };
        String[] four_digits =
        {
            "Null", "Eintausend", "Zweitausend", "Dreitausend", "Viertausend", "Fünftausend", "Sechstausend", "Siebentausend", "Achttausend", "Neuntausend"
        };

        Map<Integer, String> double_digit_thousands = new HashMap<>();
        for (int i = 10; i < 100; i++)
        {
            double_digit_thousands.put(i,
                                       i + "tausend");
        }

        Map<Integer, String> millions = new HashMap<>();
        for (int i = 1; i < 1000; i++)
        {
            millions.put(i,
                         i + "Millionen");
        }

        number_to_name.put(1,
                           single_digits);
        number_to_name.put(2,
                           two_digits);
        number_to_name.put(3,
                           three_digits);
        number_to_name.put(4,
                           four_digits);

        // Wertebereichsprüfung
        if (Number < 0 || Number > 999999999)
        {
            throw new Exception("Unzulässige Zahl!");
        }

        // die Zahl als Zeichenkette
        String number_string = String.valueOf(Number);

        // die Anzahl der Ziffern
        int digit_count = number_string.length();

        System.out.println("Die Zahl: " + Number);
        System.out.println("Anzahl Ziffern: " + digit_count);

        // Logik & Ausgabe der Zahlenübersetzungen
        int j, k;
        char current_digit;
        int current_digit_as_int;
        String current_digit_as_string;
        char first_digit;
        char second_digit;
        String first_and_second_digit_as_string;
        int first_and_second_digit_as_int;
        String output = "";
        String[] output_values = new String[2];
        String output_before;
        switch (digit_count)
        {
            case 0:
                output = String.format("Name der Zahl: %s",
                                       single_digits[0]);
                break;
            case 1:
                output = String.format("Name der Zahl: %s",
                                       single_digits[Number]);
                break;
            case 2:
                output = String.format("Name der Zahl: %s",
                                       printLastTwoDigits(
                                           digit_count,
                                           0,
                                           number_string,
                                           number_to_name));
                break;
            case 3:
                // 1. Ziffer sofort einhängen
                current_digit = number_string.charAt(0);
                current_digit_as_int = Character.getNumericValue(
                    current_digit);
                current_digit_as_string = number_to_name.get(3)[current_digit_as_int];

                output_before = current_digit_as_string;

                output = String.format("Name der Zahl: %s %s",
                                       output_before,
                                       printLastTwoDigits(
                                           digit_count,
                                           1,
                                           number_string,
                                           number_to_name));
                break;
            case 4:
                // 1. + 2. Ziffer sofort einhängen
                current_digit = number_string.charAt(0);
                current_digit_as_int = Character.getNumericValue(
                    current_digit);
                current_digit_as_string = number_to_name.get(4)[current_digit_as_int];
                output_before = current_digit_as_string;

                current_digit = number_string.charAt(1);
                current_digit_as_int = Character.getNumericValue(
                    current_digit);
                current_digit_as_string = number_to_name.get(3)[current_digit_as_int];
                output_before = output_before + " " + current_digit_as_string;

                output = String.format("Name der Zahl: %s %s",
                                       output_before,
                                       printLastTwoDigits(
                                           digit_count,
                                           2,
                                           number_string,
                                           number_to_name));
                break;
            case 5:
                // 1. + 2. Ziffer mit den double_digit_thousands darstellen
                first_digit = number_string.charAt(0);
                second_digit = number_string.charAt(1);
                first_and_second_digit_as_string = new StringBuilder(2).
                    append(first_digit).
                    append(second_digit).
                    toString();
                first_and_second_digit_as_int = Integer.parseInt(
                    first_and_second_digit_as_string);
                current_digit_as_string = double_digit_thousands.get(
                    first_and_second_digit_as_int);
                output_before = current_digit_as_string;

                // 3. Ziffer
                current_digit = number_string.charAt(2);
                current_digit_as_int = Character.getNumericValue(
                    current_digit);
                current_digit_as_string = number_to_name.get(3)[current_digit_as_int];
                output_before = output_before + " " + current_digit_as_string;

                output = String.format("Name der Zahl: %s %s ",
                                       output_before,
                                       printLastTwoDigits(
                                           digit_count,
                                           3,
                                           number_string,
                                           number_to_name));
                break;
            case 6:
                // 1. Ziffer mit three_digits darstellen
                current_digit = number_string.charAt(0);
                current_digit_as_int = Character.getNumericValue(
                    current_digit);
                current_digit_as_string = number_to_name.get(3)[current_digit_as_int];
                output_before = current_digit_as_string;

                // 2. + 3. Ziffer mit den double_digit_thousands darstellen
                first_digit = number_string.charAt(1);
                second_digit = number_string.charAt(2);
                first_and_second_digit_as_string = new StringBuilder(2).
                    append(first_digit).
                    append(second_digit).
                    toString();
                first_and_second_digit_as_int = Integer.parseInt(
                    first_and_second_digit_as_string);
                current_digit_as_string = double_digit_thousands.get(
                    first_and_second_digit_as_int);
                output_before = output_before + " " + current_digit_as_string + " ";

                // 4. Ziffer
                current_digit = number_string.charAt(3);
                current_digit_as_int = Character.getNumericValue(
                    current_digit);
                current_digit_as_string = number_to_name.get(3)[current_digit_as_int];
                output_before += current_digit_as_string;

                output = String.format("Name der Zahl: %s %s",
                                       output_before,
                                       printLastTwoDigits(
                                           digit_count,
                                           4,
                                           number_string,
                                           number_to_name));
                break;
            case 7:
                // 1. Ziffer mit millions darstellen
                current_digit = number_string.charAt(0);
                current_digit_as_int = Character.getNumericValue(
                    current_digit);
                current_digit_as_string = millions.get(current_digit_as_int);
                output_before = current_digit_as_string + " ";

                // 2. Ziffer mit three_digits darstellen
                current_digit = number_string.charAt(1);
                current_digit_as_int = Character.getNumericValue(
                    current_digit);
                current_digit_as_string = number_to_name.get(3)[current_digit_as_int];
                output_before += current_digit_as_string;

                // 3. + 4. Ziffer mit den double_digit_thousands darstellen
                first_digit = number_string.charAt(2);
                second_digit = number_string.charAt(3);
                first_and_second_digit_as_string = new StringBuilder(2).
                    append(first_digit).
                    append(second_digit).
                    toString();
                first_and_second_digit_as_int = Integer.parseInt(
                    first_and_second_digit_as_string);
                current_digit_as_string = double_digit_thousands.get(
                    first_and_second_digit_as_int);
                output_before = output_before + " " + current_digit_as_string + " ";

                // 5. Ziffer
                current_digit = number_string.charAt(4);
                current_digit_as_int = Character.getNumericValue(
                    current_digit);
                current_digit_as_string = number_to_name.get(3)[current_digit_as_int];
                output_before += current_digit_as_string;

                output = String.format("Name der Zahl: %s %s",
                                       output_before,
                                       printLastTwoDigits(
                                           digit_count,
                                           5,
                                           number_string,
                                           number_to_name));
                break;
            case 8:
                // 1. + 2. Ziffer mit millions darstellen
                first_digit = number_string.charAt(0);
                second_digit = number_string.charAt(1);
                first_and_second_digit_as_string = new StringBuilder(2).
                    append(first_digit).
                    append(second_digit).
                    toString();
                first_and_second_digit_as_int = Integer.parseInt(
                    first_and_second_digit_as_string);
                current_digit_as_string = millions.get(
                    first_and_second_digit_as_int);
                output_before = current_digit_as_string + " ";

                // 3. Ziffer mit three_digits darstellen
                current_digit = number_string.charAt(2);
                current_digit_as_int = Character.getNumericValue(
                    current_digit);
                current_digit_as_string = number_to_name.get(3)[current_digit_as_int];
                output_before += current_digit_as_string;

                // 3. + 4. Ziffer mit den double_digit_thousands darstellen
                first_digit = number_string.charAt(3);
                second_digit = number_string.charAt(4);
                first_and_second_digit_as_string = new StringBuilder(2).
                    append(first_digit).
                    append(second_digit).
                    toString();
                first_and_second_digit_as_int = Integer.parseInt(
                    first_and_second_digit_as_string);
                current_digit_as_string = double_digit_thousands.get(
                    first_and_second_digit_as_int);
                output_before = output_before + " " + current_digit_as_string + " ";

                // 5. Ziffer
                current_digit = number_string.charAt(5);
                current_digit_as_int = Character.getNumericValue(
                    current_digit);
                current_digit_as_string = number_to_name.get(3)[current_digit_as_int];
                output_before += current_digit_as_string;

                output = String.format("Name der Zahl: %s %s",
                                       output_before,
                                       printLastTwoDigits(
                                           digit_count,
                                           6,
                                           number_string,
                                           number_to_name));
                break;
            case 9:
                // 1. + 2. + 3. Ziffer mit millions darstellen
                first_digit = number_string.charAt(0);
                second_digit = number_string.charAt(1);
                char third_digit = number_string.charAt(2);
                first_and_second_digit_as_string = new StringBuilder(3).
                    append(first_digit).
                    append(second_digit).
                    append(third_digit).
                    toString();
                first_and_second_digit_as_int = Integer.parseInt(
                    first_and_second_digit_as_string);
                current_digit_as_string = millions.get(
                    first_and_second_digit_as_int);
                output_before = current_digit_as_string + " ";

                // 3. Ziffer mit three_digits darstellen
                current_digit = number_string.charAt(3);
                current_digit_as_int = Character.getNumericValue(
                    current_digit);
                current_digit_as_string = number_to_name.get(3)[current_digit_as_int];
                output_before += current_digit_as_string;

                // 3. + 4. Ziffer mit den double_digit_thousands darstellen
                first_digit = number_string.charAt(4);
                second_digit = number_string.charAt(5);
                first_and_second_digit_as_string = new StringBuilder(2).
                    append(first_digit).
                    append(second_digit).
                    toString();
                first_and_second_digit_as_int = Integer.parseInt(
                    first_and_second_digit_as_string);
                current_digit_as_string = double_digit_thousands.get(
                    first_and_second_digit_as_int);
                output_before = output_before + " " + current_digit_as_string + " ";

                // 5. Ziffer
                current_digit = number_string.charAt(6);
                current_digit_as_int = Character.getNumericValue(
                    current_digit);
                current_digit_as_string = number_to_name.get(3)[current_digit_as_int];
                output_before += current_digit_as_string;

                output = String.format("Name der Zahl: %s %s",
                                       output_before,
                                       printLastTwoDigits(
                                           digit_count,
                                           7,
                                           number_string,
                                           number_to_name));
                break;
            default:
                break;
        }
        System.out.println(output + "\n");
    }

    public static String printLastTwoDigits(int DigitCount,
                                            int CountOffset,
                                            String NumberString,
                                            Map<Integer, String[]> NumberToName)
    {
        int j = 1;
        int k = 0;
        char current_digit;
        int current_digit_as_int;
        String current_digit_as_string;
        String[] output_values = new String[2];
        for (int i = DigitCount; i > CountOffset; i--)
        {
            current_digit = NumberString.charAt(i - 1);

            current_digit_as_int = Character.getNumericValue(
                current_digit);
            current_digit_as_string = NumberToName.get(j)[current_digit_as_int];
            output_values[k] = current_digit_as_string;

            j++;
            k++;
        }

        return String.format("%s",
                             String.join(" und ",
                                         output_values)
        );
    }

}
