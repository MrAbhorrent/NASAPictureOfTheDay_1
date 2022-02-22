package ru.gb.kotlin.nasapictureoftheday.ui.main.picture

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.content.res.Resources.Theme
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.content.res.ResourcesCompat.ThemeCompat
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.main_fragment.*
import ru.gb.kotlin.nasapictureoftheday.R
import java.util.*


const val PREFS_NAME = "Theme_Prefs"
const val KEY_THEME = "prefs.theme"
const val THEME_UNDEFINED = -1
const val THEME_1 = 1
const val THEME_2 = 2
const val THEME_3 = 3


class SettingsFragment : Fragment() {



    private val themeString : List<() -> String> = listOf() {
        "Theme.NASAPictureOfTheDay";
        "Theme.NASAPictureOfTheDay.Item2";
        "Theme.NASAPictureOfTheDay.Item3"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stateCheckedTheme()

        chipGroupTheme.setOnCheckedChangeListener { chipGroupTheme, position ->
            chipGroupTheme.findViewById<Chip>(position)?.let {
                var selectedTheme = "";
                var themeID = THEME_UNDEFINED
                when (it.text) {
                    getString(R.string.textChipTheme1) -> {
                        selectedTheme = "Theme.NASAPictureOfTheDay"
                        themeID = THEME_1
                    }
                    getString(R.string.textChipTheme2) -> {
                        selectedTheme = "Theme.NASAPictureOfTheDay.Item2"
                        themeID = THEME_2
                    }
                    getString(R.string.textChipTheme3) -> {
                        selectedTheme = "Theme.NASAPictureOfTheDay.Item3"
                        themeID = THEME_3
                    }
                    else -> selectedTheme = "Не выбран чип"
                }
                saveTheme(themeID)
                Toast.makeText(context, "Выбран ${it.text} \n $selectedTheme", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveTheme(theme: Int) {
        activity?.let {
            it.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
                .putInt(KEY_THEME, theme)
                .apply()
        }
        updateTheme(theme)
    }

    private fun updateTheme(theme: Int) {
        when (theme) {
            THEME_1 -> {
                requireActivity().setTheme(R.style.Theme_NASAPictureOfTheDay)
                requireActivity().recreate()
            }
            THEME_2 -> {
                requireActivity().setTheme(R.style.Theme_NASAPictureOfTheDay_Item2)
                requireActivity().recreate()
            }
            THEME_3 -> {
                requireActivity().setTheme(R.style.Theme_NASAPictureOfTheDay_Item3)
                requireActivity().recreate()
            }
        }
    }

    private fun getSavedTheme(): Int {
        var selectedTheme = THEME_UNDEFINED
        activity?.let {
            selectedTheme = it.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getInt(KEY_THEME, THEME_UNDEFINED)
        }
        return selectedTheme
    }

    private fun stateCheckedTheme() {

        when (getSavedTheme()) {
            THEME_1 -> {
                chipGroupTheme1.isChecked = true
            }
            THEME_2 -> {
                chipGroupTheme2.isChecked = true
            }
            THEME_3 -> {
                chipGroupTheme3.isChecked = true
            }
        }

    }
}
