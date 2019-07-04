package com.example.justtimer

import android.os.Bundle
import android.util.Range
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    enum class DirectionEnum {
        UP,
        DOWN
    }

    private val hourRange: Range<Int> = Range(0, 24)
    private val minuteSecondRange: Range<Int> = Range(0, 60)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        hourTxt.setText("0")
        minuteTxt.setText("0")
        secondTxt.setText("0")

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        hourUp.setOnClickListener { cycleRange(DirectionEnum.UP, hourRange, hourTxt) }
        hourDown.setOnClickListener { cycleRange(DirectionEnum.DOWN, hourRange, hourTxt) }

        minuteUp.setOnClickListener { cycleRange(DirectionEnum.UP, minuteSecondRange, minuteTxt) }
        minuteDown.setOnClickListener { cycleRange(DirectionEnum.DOWN, minuteSecondRange, minuteTxt) }

        secondUp.setOnClickListener { cycleRange(DirectionEnum.UP, minuteSecondRange, secondTxt) }
        secondDown.setOnClickListener { cycleRange(DirectionEnum.DOWN, minuteSecondRange, secondTxt) }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun cycleRange(dir: DirectionEnum, range: Range<Int>, numInput: EditText) {
        var value: Int = numInput.text.toString().toInt()
        when (dir) {
            DirectionEnum.DOWN -> value--
            DirectionEnum.UP -> value++
        }
        if (value > range.upper) value = range.lower
        if (value < range.lower) value = range.upper
        numInput.setText(value.toString())
    }
}
