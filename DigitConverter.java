import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by JayP on 2018-04-22.
 */

public class DigitConverter {
    private static final HashMap<Character, Integer> NUMS;
    private int from = 10;

    static {
        NUMS = new HashMap<>();
        NUMS.put('A', 10);
        NUMS.put('B', 11);
        NUMS.put('C', 12);
        NUMS.put('D', 13);
        NUMS.put('E', 14);
        NUMS.put('F', 15);
    }

    public DigitConverter(int from) {
        this.from = from;
    }

    // num is in base of 'from'
    // convert num to base of 'to'
    public String convertNumberTo(String num, int to) {
        int dec = convertNumberToDec(num);
        String ret;

        switch (to) {
        case 2:
            ret = convertNumberToBin(dec);
            break;
        case 10:
            ret = String.valueOf(dec);
            break;
        case 16:
            ret = convertNumberToHex(dec);
            break;
        default:
            ret = "NOT Convertible yet";
        }

        return ret;
    }

    // num is base of 'from' in string
    // convert it to decimal
    int convertNumberToDec(String num) {
        char[] digits = new StringBuilder(num.toUpperCase())
                .reverse()
                .toString()
                .toCharArray();
        int exp = 0;
        int ret = 0;

        for (char c : digits) {
            int digit = c - '0';
            // if not a number then use hash map
            if (digit < 0 || 9 < digit) {
                digit = NUMS.get(c);
            }
//            NOT WORKING FOR CHAR
//            try {
//                digit = Character.getNumericValue(c);
//            } catch (NumberFormatException e) {
//                digit = NUMS.get(c);
//            }

            ret += digit * Math.pow(from, exp);
            exp++;
        }

        return ret;
    }

    // num is decimal
    // convert it to binary
    String convertNumberToBin(int num) {
        StringBuilder sb = new StringBuilder();

        int r;
        while (0 < num) {
            r = num % 2;
            num /= 2;

            sb.append(r);
        }

        return sb.reverse().toString();
    }

    // num is decimal
    // convert it to hex
    String convertNumberToHex(int num) {
        StringBuilder sb = new StringBuilder();

        int r;
        while (0 < num) {
            r = num % 16;
            num /= 16;

            if (r < 10) {
                sb.append(r);
            } else {
                char c = (char)(r + 55);
                sb.append(c);
            }
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        DigitConverter decConv = new DigitConverter(10);
        DigitConverter binConv = new DigitConverter(2);
        DigitConverter hexConv = new DigitConverter(16);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Type number: ");
            String num = sc.next();

            System.out.println("In decimal: " + decConv.convertNumberTo(num, 10));
            System.out.println("In binary: " + decConv.convertNumberTo(num, 2));
            System.out.println("In hex: " + decConv.convertNumberTo(num, 16));
        }

    }
}
