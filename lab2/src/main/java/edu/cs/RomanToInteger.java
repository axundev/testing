package edu.cs;

/*
  @author   AlexAT
  @project   lab2
  @class  RomanToInteger
  @version  1.0.0
  @since 21.09.2024 - 23.59
*/

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    private static final Map<Character, Integer> ROMAN_VALUES = new HashMap<>();

    static {
        ROMAN_VALUES.put('I', 1);
        ROMAN_VALUES.put('V', 5);
        ROMAN_VALUES.put('X', 10);
        ROMAN_VALUES.put('L', 50);
        ROMAN_VALUES.put('C', 100);
        ROMAN_VALUES.put('D', 500);
        ROMAN_VALUES.put('M', 1000);
    }

    public static int romanToInteger(String roman) throws IllegalArgumentException {
        if (roman == null || roman.isEmpty()) {
            throw new IllegalArgumentException("Input Roman numeral is null or empty.");
        }

        if (roman.length() > 15) {
            throw new IllegalArgumentException("Input Roman numeral is too long to be valid.");
        }

        int total = 0;
        int prevValue = 0;
        int consecutiveCount = 0;
        char prevChar = ' ';

        for (int i = roman.length() - 1; i >= 0; i--) {
            char currentChar = roman.charAt(i);

            if (!ROMAN_VALUES.containsKey(currentChar)) {
                throw new IllegalArgumentException("Invalid Roman numeral character: " + currentChar);
            }

            int currentValue = ROMAN_VALUES.get(currentChar);

            if (currentChar == prevChar) {
                consecutiveCount++;
                if (consecutiveCount > 3 && (currentChar == 'I' || currentChar == 'X' || currentChar == 'C' || currentChar == 'M')) {
                    throw new IllegalArgumentException("Invalid consecutive repetitions of character: " + currentChar);
                } else if (consecutiveCount > 1 && (currentChar == 'V' || currentChar == 'L' || currentChar == 'D')) {
                    throw new IllegalArgumentException("Invalid repetition of character: " + currentChar);
                }
            } else {
                consecutiveCount = 1;
            }

            if (i < roman.length() - 1 && currentValue < ROMAN_VALUES.get(roman.charAt(i + 1))) {
                throw new IllegalArgumentException("Invalid Roman numeral order");
            }

            if (currentValue < prevValue) {
                total -= currentValue;
            } else {
                total += currentValue;
            }

            prevValue = currentValue;
            prevChar = currentChar;
        }

        return total;
    }
}
