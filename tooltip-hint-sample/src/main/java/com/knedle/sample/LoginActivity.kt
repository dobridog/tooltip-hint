package com.knedle.sample

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.TooltipCompat
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import com.knedle.tooltip.hideTooltipHint
import com.knedle.tooltip.showTooltipHint
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var tooltipHintToggle: TooltipHintToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tooltipHintToggle = TooltipHintToggle(root_view_group)

        // Pre API 26
        TooltipCompat.setTooltipText(email, resources.getString(R.string.tooltip_email))
        TooltipCompat.setTooltipText(password, resources.getString(R.string.tooltip_password))
        TooltipCompat.setTooltipText(terms, resources.getString(R.string.tooltip_terms))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        menu!!.findItem(R.id.tooltip).isVisible = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.tooltip) {
            tooltipHintToggle.toggleTooltipHint()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    /**
     * Toggle helper class
     */
    private class TooltipHintToggle(private val viewGroup: ViewGroup) {

        var toggleOn: Boolean = false

        fun toggleTooltipHint() {
            if (toggleOn) {
                viewGroup.hideTooltipHint()
            } else {
                viewGroup.showTooltipHint()
            }

            flipToggle()
        }

        private fun flipToggle() {
            toggleOn = !toggleOn
        }
    }
}
