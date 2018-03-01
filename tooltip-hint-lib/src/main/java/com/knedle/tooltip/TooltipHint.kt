package com.knedle.tooltip

import android.annotation.TargetApi
import android.os.Build
import android.view.ViewGroup
import android.widget.TextView


/**
 * Shows the tooltip hint on this view
 */
@TargetApi(Build.VERSION_CODES.O)
fun TextView.showTooltipHint() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Api26TooltipHint(this).showTooltipHint()
    } else {
        // {@link View#getTooltipText} is not available pre API 26
    }
}

/**
 * Hides the tooltip hint on this view
 */
@TargetApi(Build.VERSION_CODES.O)
fun TextView.hideTooltipHint() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Api26TooltipHint(this).hideTooltipHint()
    } else {
        // {@link View#getTooltipText} is not available pre API 26
    }
}

/**
 * Shows tooltip hint on every view in the view group that has tooltipText property
 */
@TargetApi(Build.VERSION_CODES.O)
fun ViewGroup.showTooltipHint() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        recursiveTooltipHint(this, true)
    }
}

/**
 * Hides tooltip hint on every view in the view group that has tooltipText property
 */
@TargetApi(Build.VERSION_CODES.O)
fun ViewGroup.hideTooltipHint() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        recursiveTooltipHint(this, false)
    }

}

@TargetApi(Build.VERSION_CODES.O)
private fun recursiveTooltipHint(parent: ViewGroup, show: Boolean) {
    for (i in 0 until parent.childCount) {
        val child = parent.getChildAt(i)
        if (child is ViewGroup) {
            recursiveTooltipHint(child, show)
        } else {
            if (child is TextView) {
                if (show) {
                    child.showTooltipHint()
                } else {
                    child.hideTooltipHint()
                }
            }
        }
    }
}



