package edu.cs;

/*
  @author   AlexAT
  @project   lab2
  @class  RomanToIntegerTest
  @version  1.0.0
  @since 22.09.2024 - 00.04
*/

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanToIntegerTest {

    @Test
    public void whenInputIsEmpty_ThenThrowIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RomanToInteger.romanToInteger("");
        });
        assertEquals("Input Roman numeral is null or empty.", exception.getMessage());
    }

    @Test
    public void whenInputIsNull_ThenThrowIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RomanToInteger.romanToInteger(null);
        });
        assertEquals("Input Roman numeral is null or empty.", exception.getMessage());
    }

    @Test
    public void whenInputHasInvalidCharacter_ThenThrowIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RomanToInteger.romanToInteger("XA");
        });
        assertEquals("Invalid Roman numeral character: A", exception.getMessage());
    }

    @Test
    public void whenInputHasSpaces_ThenThrowIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RomanToInteger.romanToInteger("M M");
        });
        assertEquals("Invalid Roman numeral character:  ", exception.getMessage());
    }

    @Test
    public void whenInputIsTooLong_ThenThrowIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RomanToInteger.romanToInteger("MMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        });
        assertEquals("Input Roman numeral is too long to be valid.", exception.getMessage());
    }

    @Test
    public void whenInputHasLotOfConsecutive_M_ThenThrowIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RomanToInteger.romanToInteger("MMMMMMM");
        });
        assertEquals("Invalid consecutive repetitions of character: M", exception.getMessage());
    }

    @Test
    public void whenInputHasFourConsecutive_I_ThenThrowIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RomanToInteger.romanToInteger("IIII");
        });
        assertEquals("Invalid consecutive repetitions of character: I", exception.getMessage());
    }

    @Test
    public void whenInputHasTwoConsecutive_V_ThenThrowIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RomanToInteger.romanToInteger("VV");
        });
        assertEquals("Invalid repetition of character: V", exception.getMessage());
    }

    @Test
    public void whenInputHasInvalidOrder_IL_ThenThrowIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RomanToInteger.romanToInteger("IL");
        });
        assertEquals("Invalid Roman numeral order", exception.getMessage());
    }

    @Test
    public void whenInputHasInvalidOrder_IVI_ThenThrowIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RomanToInteger.romanToInteger("IIVI");
        });
        assertEquals("Invalid Roman numeral order", exception.getMessage());
    }

}