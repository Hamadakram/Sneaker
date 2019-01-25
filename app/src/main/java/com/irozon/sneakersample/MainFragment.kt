package com.irozon.sneakersample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.irozon.sneaker.Sneaker
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btShowWarning.setOnClickListener {
            Sneaker.with(this)
                    .setTitle("Warning!!")
                    .setCornerRadius(5, 5)
                    .setMessage("This is the warning message")
                    .sneakWarning()
        }
    }
}