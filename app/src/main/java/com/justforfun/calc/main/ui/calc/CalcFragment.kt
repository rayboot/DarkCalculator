package com.justforfun.calc.main.ui.calc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.justforfun.calc.R

class CalcFragment : Fragment() {

    private lateinit var calcViewModel: CalcViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        calcViewModel =
                ViewModelProvider(this).get(CalcViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_calc, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        calcViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}