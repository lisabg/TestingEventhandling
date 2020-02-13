package com.example.lisabg_oblig1

import org.junit.Assert.*
import org.junit.Test


class ConverterActivityTest {

    private val testNum = 20.0

    @Test
    fun checkFlozToL() {
        assertEquals(0.5914, flozConvert(testNum), 0.001)
    }


    @Test
    fun checkCpToL() {
        assertEquals(4.7318, cpConvert(testNum), 0.001)
    }





    fun flozConvert (num: Double) : Double {
        return num * 0.02957
    }

    fun cpConvert (num: Double) : Double {
        return num * 0.23659
    }


}