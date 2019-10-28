package com.example.unitconverter.util

import com.example.unitconverter.R

enum class Conversion(val description: Int, val units: MutableList<Unit>) {
    AREA(R.string.area, mutableListOf(Unit.SQ_CENTIMETRES, Unit.SQ_KILOMETRES, Unit.SQ_METRES)),
    WEIGHT(R.string.weight, mutableListOf(Unit.GRAM, Unit.KILO, Unit.CENTNER, Unit.TON))

}

