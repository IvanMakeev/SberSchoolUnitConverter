package com.example.unitconverter

import com.example.unitconverter.util.Conversion
import com.example.unitconverter.util.Utils
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class UtilsTest {

    private val valuesForConvert = arrayOf(10000, 1000)
    private val outConversion = arrayOf(0, 1, 2, 3)
    private val inConversion = arrayOf(3, 2, 1, 0)
    private val resultOutToIn = arrayOf(10000.0, 10.0, 0.1, 0.01)
    private val resultInToOut = arrayOf(1000.0, 100.0, 1.0,  0.001)

    @Before
    fun setUp() {
        Utils.setConversion(Conversion.WEIGHT)
    }

    @Test
    fun calculateOutToInTest() {
        for (i in outConversion.indices) {
            assertEquals(resultOutToIn[i], Utils.calculate(valuesForConvert[0], 0, outConversion[i]))
        }
    }

    @Test
    fun calculateInToOutTest() {
        for (i in inConversion.indices) {
            assertEquals(resultInToOut[i], Utils.calculate(valuesForConvert[1], inConversion[i], 3))
        }
    }
}