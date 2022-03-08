package aliabbas.com.userrepositories.custom_views

import aliabbas.com.userrepositories.R
import aliabbas.com.scalablecodebaseapp.model.repository_commit.BarData
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat

/**
 * Created By Ali Abbas
 *
 */
class BarChartView : View {

    /**
     * Paint object used to draw things on canvas.
     */
    private val mPaint: Paint = Paint()
    private val mPaintText: Paint = Paint()

    /**
     * Context
     */
    private var mContext: Context

    /**
     * Data used to show bar charts
     */
    private var mDataArray: Array<BarData>? = null
    /**
     * Returns the maximum value in the data set.
     *
     * @return Maximum value in the data set.
     */
    /**
     * Maximum value in the data set
     */
    private var maxValueOfData = 0f

    /**
     * width of drawing
     */
    private val mStrokeWidth = 2

    /**
     * Font size for legends along X and Y axis in dp.
     */
    private val mAxisFontSize = 14

    /**
     * Distance between Axis and values shown as legend next to it (in px)
     */
    private var mDistanceAxisAndValue = 0
    /**
     * Returns maximum width occupied by any of the Y axis values.
     *
     * @return maximum width occupied by any of the Y axis values
     */
    /**
     * Maximum width of legends along Y axis
     */
    private var maxWidthOfYAxisText = 0
    /**
     * Returns the maximum height of X Axis text.
     *
     * @return the maximum height of X Axis text
     */
    /**
     * Maximum width of legends along X axis
     */
    private var maxHeightOfXAxisText = 0

    private val minimumWidthForFewerBars = 50

    /**
     * Initialize internal variables
     */
    private fun init() {
        mDistanceAxisAndValue = dpToPixels(mContext, 14f).toInt()
    }

    /**
     * This View will use the given data for drawing bar chart
     *
     * @param barData data to be used for drawing bar chart.
     */
    fun setBarDataToDisplay(barData: Array<BarData>) {
        mDataArray = barData
        maxValueOfData = Float.MIN_VALUE
        for (index in mDataArray!!.indices) {
            if (maxValueOfData < mDataArray!![index].value) maxValueOfData =
                (mDataArray!![index].value + 10).toFloat()
        }
        findMaxWidthOfText(barData)
        invalidate()
    }

    /**
     * Calculate the maximum width occupied by any of given bar chart data. Width is calculated
     * based on default font used and size specified in [.mAxisFontSize].
     *
     * @param barData data to be used in bar chart
     */
    private fun findMaxWidthOfText(barData: Array<BarData>) {
        maxWidthOfYAxisText = Int.MIN_VALUE
        maxHeightOfXAxisText = Int.MIN_VALUE
        val paint = Paint()
        paint.typeface = Typeface.DEFAULT
        paint.textSize = dpToPixels(mContext, mAxisFontSize.toFloat())
        val bounds = Rect()
        for (index in mDataArray!!.indices) {
            val currentTextWidth =
                paint.measureText(barData[index].value.toFloat().toString())
                    .toInt()
            if (maxWidthOfYAxisText < currentTextWidth) maxWidthOfYAxisText = currentTextWidth
            mPaint.getTextBounds(
                barData[index].monthName, 0,
                barData[index].monthName.length, bounds
            )
            mPaintText.getTextBounds(
                barData[index].monthName, 0,
                barData[index].monthName.length, bounds
            )
            if (maxHeightOfXAxisText < bounds.height()) maxHeightOfXAxisText = bounds.height()
        }
    }

    override fun onDraw(canvas: Canvas) {
        val usableViewHeight = height - paddingBottom - paddingTop
        val usableViewWidth = width - paddingLeft - paddingRight
        val origin = origin
        mPaint.color = ContextCompat.getColor(mContext, R.color.black)
        mPaintText.color = ContextCompat.getColor(mContext, R.color.taupe_color)
        mPaint.strokeWidth = mStrokeWidth.toFloat()
        mPaintText.strokeWidth = mStrokeWidth.toFloat()
        if (mDataArray == null || mDataArray!!.isEmpty()) return
        //draw bar chart
        val barAndVacantSpaceCount = (mDataArray!!.size shl 1) + 1
        var widthFactor = (usableViewWidth - maxWidthOfYAxisText) / barAndVacantSpaceCount
        if (mDataArray!!.size < 5) {
            widthFactor = minimumWidthForFewerBars
        }
        var x1: Int
        var x2: Int
        var y1: Int
        var y2: Int
        val maxValue = maxValueOfData
        for (index in mDataArray!!.indices) {
            x1 = origin.x + ((index shl 1) + 1) * widthFactor
            x2 = origin.x + ((index shl 1) + 2) * widthFactor
            val barHeight = ((usableViewHeight - xAxisLabelAndMargin) *
                    mDataArray!![index].value / maxValue).toInt()
            y1 = origin.y - barHeight
            y2 = origin.y
            canvas.drawRect(x1.toFloat(), y1.toFloat(), x2.toFloat(), y2.toFloat(), mPaintText)
            showBarChartLabels(
                mDataArray!![index].monthName, x1 + (x2 - x1) / 2,
                origin.y + mDistanceAxisAndValue + maxHeightOfXAxisText, canvas
            )
            showBarChartLabels(mDataArray!![index].value.toString(), x1 + (x2 - x1) / 2, y1, canvas)
        }
    }

    /**
     * Draws X axis labels.
     *
     * @param label   label to be drawn below a bar along X axis
     * @param centerX center x coordinate of the given bar
     * @param canvas  canvas to draw the chart
     */
    private fun showBarChartLabels(label: String, centerX: Int, y1: Int, canvas: Canvas) {
        val bounds = Rect()
        mPaint.getTextBounds(label, 0, label.length, bounds)
        val x = centerX - bounds.width() / 2
        mPaint.textSize = dpToPixels(mContext, mAxisFontSize.toFloat())
        mPaint.typeface = Typeface.DEFAULT
        canvas.drawText(label, x.toFloat(), y1.toFloat(), mPaint)
    }

    /**
     * Returns the X axis' maximum label height and margin between label and the X axis.
     *
     * @return the X axis' maximum label height and margin between label and the X axis
     */
    private val xAxisLabelAndMargin: Int
        get() = maxHeightOfXAxisText + mDistanceAxisAndValue

    /**
     * Returns the origin coordinates in canvas' coordinates.
     *
     * @return origin's coordinates
     */
    private val origin: Point
        get() = if (mDataArray != null) {
            Point(
                paddingLeft + maxWidthOfYAxisText + mDistanceAxisAndValue,
                height - paddingBottom - xAxisLabelAndMargin
            )
        } else {
            Point(
                paddingLeft + maxWidthOfYAxisText + mDistanceAxisAndValue,
                height - paddingBottom
            )
        }

    companion object {
        /**
         * Convert dp value to pixels.
         *
         * @param context Context
         * @param dpValue Value in dip
         * @return Values in pixels
         */
        fun dpToPixels(context: Context?, dpValue: Float): Float {
            if (context != null) {
                val metrics = context.resources.displayMetrics
                return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, metrics)
            }
            return 0f
        }
    }

    //Initializing method
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        mContext = context
        //mPaint = Paint()
        init()
    }

    constructor(context: Context) : super(context) {
        mContext = context
        //mPaint = Paint()
        init()
    }
    /*init {
        init()
    }*/
}