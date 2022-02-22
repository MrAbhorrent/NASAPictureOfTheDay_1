package ru.gb.kotlin.nasapictureoftheday

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_settings.*
import ru.gb.kotlin.nasapictureoftheday.ui.main.MainFragment
import ru.gb.kotlin.nasapictureoftheday.ui.main.picture.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        initTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }

    private fun getSavedTheme(): Int {
        var selectedTheme = THEME_UNDEFINED
        selectedTheme = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getInt(KEY_THEME, THEME_UNDEFINED)
        return selectedTheme
    }

    private fun initTheme() {

        when (getSavedTheme()) {
            THEME_1 -> {
                setTheme(R.style.Theme_NASAPictureOfTheDay)
            }
            THEME_2 -> {
                setTheme(R.style.Theme_NASAPictureOfTheDay_Item2)
            }
            THEME_3 -> {
                setTheme(R.style.Theme_NASAPictureOfTheDay_Item3)
            }
        }
    }
}