package com.example.unitconverter.util

object Utils {

    private lateinit var conversion: Conversion

    @JvmStatic
    fun calculate(enteredValue: Int, positionOutConversion: Int, positionInConversion: Int): Double {
        var resultValue: Double = enteredValue.toDouble()
        val outConversion = conversion.units[positionOutConversion]
        val inConversion = conversion.units[positionInConversion]

        return when {
            outConversion.priority > inConversion.priority -> {
                resultValue *= outConversion.conversionBase
                resultValue /= inConversion.conversionBase
                resultValue
            }
            outConversion.priority < inConversion.priority -> {
                resultValue *= outConversion.conversionBase
                resultValue /= inConversion.conversionBase
                resultValue
            }
            else -> {
                resultValue
            }
        }
    }

    @JvmStatic
    fun setConversion(conversion: Conversion) {
        this.conversion = conversion
    }
}