package com.knedle.tooltip

import android.annotation.TargetApi
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.TextView

@TargetApi(Build.VERSION_CODES.O)
class Api26TooltipHint(private val textView: TextView) {

    private var tooltipAnchor: Int
    private var tooltipDrawable: Drawable?
    private var tooltipDrawablePadding: Float

    init {
        val anchorAttr = textView.context.obtainStyledAttributes(R.style.TooltipHint, intArrayOf(R.attr.tooltipAnchor))
        tooltipAnchor = anchorAttr.getInteger(0, 0)
        anchorAttr.recycle()

        val drawableAttr = textView.context.obtainStyledAttributes(R.style.TooltipHint, intArrayOf(android.R.attr.drawable))
        tooltipDrawable = drawableAttr.getDrawable(0)
        drawableAttr.recycle()

        val paddingAttr = textView.context.obtainStyledAttributes(R.style.TooltipHint, intArrayOf(android.R.attr.drawablePadding))
        tooltipDrawablePadding = paddingAttr.getDimension(0, 0f)
        paddingAttr.recycle()
    }

    private fun hasTooltip(): Boolean {
        return textView.tooltipText != null
    }

    /**
     * Shows the tooltip hint on this view
     */
    fun showTooltipHint() {
        if (hasTooltip()) {
            // save view's drawables
            textView.setTag(R.id.tooltip_compound_drawables_key, textView.compoundDrawables)

            // set drawables
            val drawables = textView.compoundDrawables
            drawables[tooltipAnchor] = tooltipDrawable
            textView.setCompoundDrawablesWithIntrinsicBounds(
                    drawables[0],
                    drawables[1],
                    drawables[2],
                    drawables[3])

            // set padding
            if (textView.compoundDrawablePadding == 0) {
                textView.compoundDrawablePadding = tooltipDrawablePadding.toInt()
            }
        }
    }

    /**
     * Hides the tooltip hint on this view
     */
    fun hideTooltipHint() {
        // restore view's drawables
        val tag = textView.getTag(R.id.tooltip_compound_drawables_key)
        if (tag != null) {
            val drawables = tag as Array<Drawable>
            textView.setCompoundDrawablesWithIntrinsicBounds(
                    drawables[0],
                    drawables[1],
                    drawables[2],
                    drawables[3])

            // clear stored drawables
            textView.setTag(R.id.tooltip_compound_drawables_key, null)
        }
    }
}