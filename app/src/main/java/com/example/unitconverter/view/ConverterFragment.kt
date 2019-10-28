package com.example.unitconverter.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.unitconverter.R
import com.example.unitconverter.util.Conversion
import com.example.unitconverter.util.Unit
import com.example.unitconverter.util.Utils
import kotlinx.android.synthetic.main.fragment_converter.*

class ConverterFragment : Fragment() {

    companion object {
        const val CONVERSION = "CONVERSION"
        @JvmStatic
        fun newInstance(conversion: Conversion): ConverterFragment {
            val args = Bundle()
            args.putSerializable(CONVERSION, conversion)
            val fragment = ConverterFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var list: List<String>
    private lateinit var conversion: Conversion
    private var positionOutConversion: Int = 0
    private var positionInConversion: Int = 0
    private var parseInt = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            conversion = it.getSerializable(CONVERSION) as Conversion
            Utils.setConversion(conversion)
            list = convertMethod(conversion.units)
        }
        initSpinner()
        initEditText()
    }

    private fun initSpinner() {
        out_convert_spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list)
        out_convert_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //nothing to do
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                positionOutConversion = position
                out_convert_description.text = getString(conversion.units[position].measuring)
                if (parseInt != 0) updateScreen()
            }
        }

        in_convert_spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list)
        in_convert_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //nothing to do
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
        in_convert_value.setText(
            Utils.calculate(
                parseInt,
                positionOutConversion,
                positionInConversion
            ).toString()
        )
    }

    private fun convertMethod(list: MutableList<Unit>): List<String> {
        val elementList = ArrayList<String>()
        list.forEach {
            elementList.add(getString(it.measuring))
        }
        return elementList
    }
}