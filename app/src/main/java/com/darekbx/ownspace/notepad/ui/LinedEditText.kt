package com.darekbx.ownspace.notepad.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class LinedEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    val paint = Paint().apply {
        color = Color.parseColor("#DDDDDD")
        strokeWidth = 1.0F
    }

    private fun Canvas.drawLine(startX: Int, startY: Int, stopX: Int, stopY: Int, paint: Paint) {
        drawLine(startX.toFloat(), startY.toFloat(), stopX.toFloat(), stopY.toFloat(), paint)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            val height = canvas.height
            var curHeight = 0
            val lineBounds = Rect()
            val baseline = getLineBounds(0, lineBounds)
            curHeight = baseline + 1

            while (curHeight < height) {
                canvas.drawLine(lineBounds.left, curHeight, lineBounds.right, curHeight, paint)
                curHeight += lineHeight
            }
        }
        super.onDraw(canvas)
    }
}