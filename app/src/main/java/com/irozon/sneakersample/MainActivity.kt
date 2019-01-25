package com.irozon.sneakersample

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.irozon.sneaker.Sneaker
import com.irozon.sneaker.interfaces.OnSneakerDismissListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(supportFragmentManager.beginTransaction()) {
            this.add(R.id.fragment, MainFragment())
            this.commit()
        }

        btShowError.setOnClickListener {
            Sneaker.with(this)
                    .setTitle("Error!!")
                    .setMessage("This is the error message")
                    .setTypeface(Typeface.createFromAsset(this.assets, "font/Slabo27px-Regular.ttf"))
                    .sneakError()
        }
        btShowSuccess.setOnClickListener {
            val sneaker = Sneaker.with(viewGroup)
            val view = LayoutInflater.from(this).inflate(R.layout.custom_view, sneaker.getView(), false)
            view.findViewById<TextView>(R.id.tvInstall).setOnClickListener{
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
            }
            sneaker.sneakCustom(view)
        }
    }
}