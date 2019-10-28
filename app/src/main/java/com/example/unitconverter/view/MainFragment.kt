package com.example.unitconverter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unitconverter.R
import com.example.unitconverter.adapter.DimensionAdapter
import com.example.unitconverter.util.Conversion
import com.example.unitconverter.util.MainItemClickListener
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), MainItemClickListener {
    companion object {
        @JvmStatic
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayout = LinearLayoutManager(requireContext())
        val adapter = DimensionAdapter(Conversion.values(), this)
        recycler.layoutManager = linearLayout
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun setMainClickListener(conversion: Conversion) {
        requireFragmentManager()
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.root_layout, ConverterFragment.newInstance(conversion))
            .commit()
    }
}