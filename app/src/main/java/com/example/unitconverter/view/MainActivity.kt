package com.example.unitconverter.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unitconverter.adapter.DimensionAdapter
import com.example.unitconverter.R
import com.example.unitconverter.util.Conversion
import com.example.unitconverter.util.MainItemClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainItemClickListener {

    companion object {
        const val CONVERSION = "CONVERSION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val linearLayout = LinearLayoutManager(this)
        val adapter = DimensionAdapter(Conversion.values(), this)
        recycler.layoutManager = linearLayout
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    override fun setMainClickListener(conversion: Conversion) {
        val intent = Intent(this, ConverterActivity::class.java)
        intent.putExtra(CONVERSION, conversion)
        startActivity(intent)
    }
}
