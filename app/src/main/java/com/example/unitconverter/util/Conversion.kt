package com.example.unitconverter.util

import com.example.unitconverter.R
import java.util.*

enum class Conversion(val description: Int, val units: MutableList<Unit>) {
    AREA(R.string.area, Arrays.asList(Unit.SQ_CENTIMETRES, Unit.SQ_KILOMETRES, Unit.SQ_METRES)),
    WEIGHT(R.string.weight, Arrays.asList(Unit.GRAM, Unit.KILO, Unit.CENTNER, Unit.TON))

}

