package com.yulie.graph.view.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class GraphViewAdapter(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val dataSet = mutableListOf<DataPoint>()


    private var xMin = 0
    private var xMax = 0
    private var yMin = 0
    private var yMax = 0

    private val dataPointPaint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 7f
        style = Paint.Style.STROKE
    }

    private val dataPointFillPaint = Paint().apply {
        color = Color.WHITE
    }

    private val dataPointLinePaint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 7f
        isAntiAlias = true
    }

    private val axisLinePaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 10f
    }

    private val textPaint = Paint().apply {
        isAntiAlias = true
        textSize = 20F
        letterSpacing = 1F
        setShadowLayer(1f, x, y, Color.BLACK)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.translate(0f, height.toFloat());
        canvas.scale(1f, -1f);

        dataSet.forEachIndexed { index, currentDataPoint ->
            val realX = currentDataPoint.xVal.toRealX()
            val realY = currentDataPoint.yVal.toRealY()

            if (index < dataSet.size - 1) {
                val nextDataPoint = dataSet[index + 1]
                val startX = currentDataPoint.xVal.toRealX()
                val startY = currentDataPoint.yVal.toRealY()
                val endX = nextDataPoint.xVal.toRealX()
                val endY = nextDataPoint.yVal.toRealY()
                canvas.drawLine(startX, startY, endX, endY, dataPointLinePaint)
            }


            canvas.drawCircle(realX, realY, 7f, dataPointFillPaint)
            canvas.drawCircle(realX, realY, 7f, dataPointPaint)
        }


        canvas.drawLine(0f, 0f, 0f, height.toFloat(), axisLinePaint)  //y
        canvas.drawLine(0f, 0f, width.toFloat(), 0f, axisLinePaint) //x

/*
        canvas.drawText("Speed (m/k) ",100f, 785f,textPaint)

        canvas.save();
        canvas.rotate(90f, 0f, 0f);
        canvas.drawText("Power",500f,0f,textPaint)
        canvas.restore();*/
    }

    fun setData(newDataSet: ArrayList<DataPoint>) {
        xMin = newDataSet.maxByOrNull { it.xVal }?.xVal ?: 0
        xMax = newDataSet.maxByOrNull { it.xVal }?.xVal ?: 0
        yMin = newDataSet.maxByOrNull { it.yVal }?.yVal ?: 0
        yMax = newDataSet.maxByOrNull { it.yVal }?.yVal ?: 0
        dataSet.clear()
        dataSet.addAll(newDataSet)
        invalidate()

    }

    private fun Int.toRealX() = toFloat() / xMax * width
    private fun Int.toRealY() = toFloat() / yMax * height


}


data class DataPoint(
    val xVal: Int,
    val yVal: Int
)
