package edu.cs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/*
  @author   AlexAT
  @project   lab1
  @class  IntegerToRomanTest
  @version  1.0.0
  @since 10.09.2024 - 19.49
*/

public class IntegerToRomanTest {
    @Test
    public void whenNumberLowerThan_1ThenReturnNull() {
        assertNull(IntegerToRoman.integerToRoman(0));
    }

    @Test
    public void whenNumberGreaterThan_3999ThenReturnNull() {
        assertNull(IntegerToRoman.integerToRoman(4000));
    }

    @Test
    public void whenNumberIsNegativeThenReturnNull() {
        assertNull( IntegerToRoman.integerToRoman(-5));
    }

    @Test
    public void whenNumberIs_1ThenReturn_I() {
        assertEquals("I", IntegerToRoman.integerToRoman(1));
    }

    @Test
    public void whenNumberIs_3ThenReturn_III() {
        assertEquals("III", IntegerToRoman.integerToRoman(3));
    }

    @Test
    public void whenNumberIs_4ThenReturn_IV() {
        assertEquals("IV", IntegerToRoman.integerToRoman(4));
    }

    @Test
    public void whenNumberIs_9ThenReturn_IX() {
        assertEquals("IX", IntegerToRoman.integerToRoman(9));
    }

    @Test
    public void whenNumberIs_34ThenReturn_XXXIV() {
        assertEquals("XXXIV", IntegerToRoman.integerToRoman(34));
    }

    @Test
    public void whenNumberIs_42ThenReturn_LVIII() {
        assertEquals("XLII", IntegerToRoman.integerToRoman(42));
    }

    @Test
    public void whenNumberIs_58ThenReturn_LVIII() {
        assertEquals("LVIII", IntegerToRoman.integerToRoman(58));
    }

    //20 тестів:

    @Test
    public void whenNumberIs_90ThenReturn_XC() {
        assertEquals("XC", IntegerToRoman.integerToRoman(90));
    }

    @Test
    public void whenNumberIs_164ThenReturn_CLXIV() {
        assertEquals("CLXIV", IntegerToRoman.integerToRoman(164));
    }

    @Test
    public void whenNumberIs_381ThenReturn_CLXIV() {
        assertEquals("CCCLXXXI", IntegerToRoman.integerToRoman(381));
    }

    @Test
    public void whenNumberIs_401ThenReturn_CDI() {
        assertEquals("CDI", IntegerToRoman.integerToRoman(401));
    }

    @Test
    public void whenNumberIs_499ThenReturn_CDXCIX() {
        assertEquals("CDXCIX", IntegerToRoman.integerToRoman(499));
    }

    @Test
    public void whenNumberIs_555ThenReturn_DLV() {
        assertEquals("DLV", IntegerToRoman.integerToRoman(555));
    }

    @Test
    public void whenNumberIs_700ThenReturn_DCC() {
        assertEquals("DCC", IntegerToRoman.integerToRoman(700));
    }

    @Test
    public void whenNumberIs_999ThenReturn_CMXCIX() {
        assertEquals("CMXCIX", IntegerToRoman.integerToRoman(999));
    }

    @Test
    public void whenNumberIs_1000ThenReturn_M() {
        assertEquals("M", IntegerToRoman.integerToRoman(1000));
    }

    @Test
    public void whenNumberIs_1994ThenReturn_MCMXCIV() {
        assertEquals("MCMXCIV", IntegerToRoman.integerToRoman(1994));
    }

    //30 tests:

    @Test
    public void whenNumberIs_3549ThenReturn_MMMDXLIX() {
        assertEquals("MMMDXLIX", IntegerToRoman.integerToRoman(3549));
    }

    @Test
    public void whenNumberIs_3999ThenReturn_MMMCMXCIX() {
        assertEquals("MMMCMXCIX", IntegerToRoman.integerToRoman(3999));
    }

    @Test
    public void whenNumberIs_99ThenReturn_XCIX() {
        assertEquals("XCIX", IntegerToRoman.integerToRoman(99));
    }

    @Test
    public void whenNumberIs_1531ThenReturn_M() {
        assertEquals("MDXXXI", IntegerToRoman.integerToRoman(1531));
    }

    @Test
    public void whenNumberIs_900ThenReturn_CM() {
        assertEquals("CM", IntegerToRoman.integerToRoman(900));
    }

    @Test
    public void whenNumberIs_1500ThenReturn_MD() {
        assertEquals("MD", IntegerToRoman.integerToRoman(1500));
    }

    @Test
    public void whenNumberIs_399ThenReturn_CCCXCIX() {
        assertEquals("CCCXCIX", IntegerToRoman.integerToRoman(399));
    }

    @Test
    public void whenNumberIs_600ThenReturn_DC() {
        assertEquals("DC", IntegerToRoman.integerToRoman(600));
    }

    @Test
    public void whenNumberIs_42ThenReturn_XLII() {
        assertEquals("XLII", IntegerToRoman.integerToRoman(42));
    }

    @Test
    public void whenNumberIs_501ThenReturn_DI() {
        assertEquals("DI", IntegerToRoman.integerToRoman(501));
    }

    //40 tests:

    @Test
    public void whenNumberIs_47ThenReturn_XLVII() {
        assertEquals("XLVII", IntegerToRoman.integerToRoman(47));
    }

    @Test
    public void whenNumberIs_444ThenReturn_CDXLIV() {
        assertEquals("CDXLIV", IntegerToRoman.integerToRoman(444));
    }

    @Test
    public void whenNumberIs_888ThenReturn_DCCCLXXXVIII() {
        assertEquals("DCCCLXXXVIII", IntegerToRoman.integerToRoman(888));
    }

    @Test
    public void whenNumberIs_276ThenReturn_CCLXXVI() {
        assertEquals("CCLXXVI", IntegerToRoman.integerToRoman(276));
    }

    @Test
    public void whenNumberIs_2023ThenReturn_MMXXIII() {
        assertEquals("MMXXIII", IntegerToRoman.integerToRoman(2023));
    }

    @Test
    public void whenNumberIs_1666ThenReturn_MDCLXVI() {
        assertEquals("MDCLXVI", IntegerToRoman.integerToRoman(1666));
    }

    @Test
    public void whenNumberIs_333ThenReturn_CCCXXXIII() {
        assertEquals("CCCXXXIII", IntegerToRoman.integerToRoman(333));
    }

    @Test
    public void whenNumberIs_76ThenReturn_LXXVI() {
        assertEquals("LXXVI", IntegerToRoman.integerToRoman(76));
    }

    @Test
    public void whenNumberIs_789ThenReturn_DCCLXXXIX() {
        assertEquals("DCCLXXXIX", IntegerToRoman.integerToRoman(789));
    }

    @Test
    public void whenNumberIs_143ThenReturn_CXLIII() {
        assertEquals("CXLIII", IntegerToRoman.integerToRoman(143));
    }

}
