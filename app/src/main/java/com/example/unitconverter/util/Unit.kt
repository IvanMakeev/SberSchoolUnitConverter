package com.example.unitconverter.util

import com.example.unitconverter.R

enum class Unit(
    val measuring: Int,
    val conversionBase: Double,
    val priority: Int
) {
    SQ_KILOMETRES(R.string.kilometres, 1000.0, 3),
    SQ_METRES(R.string.metres, 1.0, 2),
    SQ_CENTIMETRES(R.string.centimetres, 0.01, 1),

    TON(R.string.ton, 1000.0, 4),
    CENTNER(R.string.centner, 100.0, 3),
    KILO(R.string.kilo, 1.0, 2),
    GRAM(R.string.gram, 0.001, 1)

}