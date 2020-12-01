package com.justforfun.calc.main.ui.calc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.justforfun.calc.MainActivity
import com.justforfun.calc.R
import com.justforfun.calc.calc.big.BigDecimalActivity
import com.justforfun.calc.calc.capital.CapitalMoneyActivity

class CalcFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_calc, container, false)

        root.findViewById<View>(R.id.enter_calc_1).setOnClickListener {
            MainActivity.actionStart(requireContext())
        }
        root.findViewById<View>(R.id.enter_calc_2).setOnClickListener {
            BigDecimalActivity.actionStart(requireContext())
        }
        root.findViewById<View>(R.id.enter_calc_3).setOnClickListener {
            CapitalMoneyActivity.actionStart(requireContext())
        }

        return root
    }
}