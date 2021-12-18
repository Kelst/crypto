package com.gamewar.cryptotrading.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.gamewar.cryptotrading.R

@SuppressLint("ViewConstructor")
class CustomMarkerView(context: Context, layoutResource: Int) : MarkerView(context, layoutResource) {

    private var mOffset: MPPointF? = null

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        super.refreshContent(e, highlight)
        val date = findViewById<TextView>(R.id.tvMarkerDate)
        val value = findViewById<TextView>(R.id.tvMarkerValue)

        value.text = "₹ ${e!!.y.toString()}"
        date.text = DateConverter.getDateWithMonthAndYear(e.x.toLong())
    }

    override fun getOffset(): MPPointF {
        if (mOffset == null) {
            mOffset = MPPointF((-(width/2)).toFloat(), (-height).toFloat())
        }
        return mOffset as MPPointF
    }

}