package com.justforfun.calc.main.ui.memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.justforfun.calc.R

class MemoFragment : Fragment() {

    private lateinit var memoViewModel: MemoViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        memoViewModel =
                ViewModelProvider(this).get(MemoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_memo, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        memoViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}