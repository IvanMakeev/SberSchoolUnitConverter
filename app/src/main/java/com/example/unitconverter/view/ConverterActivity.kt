package com.example.unitconverter.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.unitconverter.R
import com.example.unitconverter.util.Conversion
import com.example.unitconverter.util.Unit
import kotlinx.android.synthetic.main.activity_converter.*

class ConverterActivity : AppCompatActivity() {

    companion object {
        const val CONVERSION = "CONVERSION"
    }

    private lateinit var list: List<String>
    private lateinit var conversion: Conversion
    private var positionOutConversion: Int = 0
    private var positionInConversion: Int = 0
    private var parseInt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        conversion = intent.getSerializableExtra(CONVERSION) as Conversion
        list = convertMethod(conversion.units)
        initSpinner()
        initEditText()
    }

    private fun initSpinner() {
        out_convert_spinner.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        out_convert_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                positionOutConversion = position
                out_convert_description.text = getString(conversion.units[position].measuring)
                if (parseInt != 0) updateScreen()
            }
        }

        in_convert_spinner.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        in_convert_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                positionInConversion = position
                in_convert_description.text = getString(conversion.units[position].measuring)
                if (parseInt != 0) updateScreen()
            }
        }
    }

    private fun initEditText() {
        in_convert_value.isEnabled = false
        out_convert_value.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                parseInt = try {
                    s.toString().toInt()
                } catch (ex: NumberFormatException) {
                    0
                }
                updateScreen()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    private fun updateScreen() {
        in_convert_value.setText(calculate(parseInt).toString())
    }

    private fun calculate(enteredValue: Int): Double {
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


    private fun convertMethod(list: MutableList<Unit>): List<String> {
        val elementList = ArrayList<String>()
        list.forEach {
            elementList.add(getString(it.measuring))
        }
        return elementList
    }
}