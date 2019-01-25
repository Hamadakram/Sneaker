package com.irozon.sneaker

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.v7.widget.AppCompatImageView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.irozon.sneaker.Utils.customView
import com.irozon.sneaker.widget.RoundedImageView

internal class SneakerView(context: Context?) : LinearLayout(context) {
    init {
        id = R.id.mainLayout
        layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private val DEFAULT_VALUE = -100000


    fun setIcon(icon: Drawable?, isCircular: Boolean, iconSize: Int, colorFilter: Int) {
        icon?.let {
            val ivIcon =
                    if (!isCircular) AppCompatImageView(context)
                    else RoundedImageView(context)
            ivIcon.layoutParams = LinearLayout.LayoutParams(iconSize, iconSize)
            ivIcon.setImageDrawable(it)
            ivIcon.isClickable = false
            if (colorFilter != DEFAULT_VALUE) ivIcon.setColorFilter(colorFilter)
            addView(ivIcon, 0)
        }
    }

    fun setTextContent(title: String, titleColor: Int, description: String, messageColor: Int, typeface: Typeface?) {
        // Title and description
        val textLayout = LinearLayout(context)
        val textLayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        textLayout.layoutParams = textLayoutParams
        textLayout.orientation = LinearLayout.VERTICAL

        // Title
        if (!title.isEmpty()) {
            val tvTitle = TextView(context)

            tvTitle.layoutParams = textLayoutParams
            tvTitle.gravity = Gravity.CENTER_VERTICAL
            tvTitle.textSize = 14f
            tvTitle.text = title
            tvTitle.isClickable = false

            tvTitle.setPadding(46, if (description.isNotEmpty()) 26 else 0, 26, 0) // Top padding only if there is message
            if (titleColor != DEFAULT_VALUE) tvTitle.setTextColor(titleColor)
            if (typeface != null) tvTitle.typeface = typeface

            textLayout.addView(tvTitle)
        }

        // Description
        if (!description.isEmpty()) {
            val tvMessage = TextView(context)
            tvMessage.layoutParams = textLayoutParams
            tvMessage.gravity = Gravity.CENTER_VERTICAL
            tvMessage.textSize = 12f
            tvMessage.text = description
            tvMessage.isClickable = false

            tvMessage.setPadding(46, 0, 26, if (title.isNotEmpty()) 26 else 0) // Top padding only if there is message
            if (messageColor != DEFAULT_VALUE) tvMessage.setTextColor(messageColor)
            if (typeface != null) tvMessage.typeface = typeface

            textLayout.addView(tvMessage)
        }
        addView(textLayout)
    }

    fun setBackground(color: Int, cornerRadius: Int) {
        if (cornerRadius == DEFAULT_VALUE) setBackgroundColor(color)
        else background = customView(context, color, cornerRadius)
    }

    fun setCustomView(view: View) {
        addView(view, 0)
    }
}