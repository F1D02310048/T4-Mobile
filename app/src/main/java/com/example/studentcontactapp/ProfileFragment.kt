package com.example.studentcontactapp

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.studentcontactapp.utils.PrefManager
import com.example.studentcontactapp.SettingsManager
import com.google.android.material.switchmaterial.SwitchMaterial

class ProfileFragment : Fragment() {

    private lateinit var prefManager: PrefManager
    private lateinit var settingsManager: SettingsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        prefManager = PrefManager(requireContext())
        settingsManager = SettingsManager(requireContext())

        val tvProfileName = rootView.findViewById<TextView>(R.id.tvProfileName)
        val switchDarkMode = rootView.findViewById<SwitchMaterial>(R.id.switchDarkMode)
        val switchFontSize = rootView.findViewById<SwitchMaterial>(R.id.switchFontSize)
        val switchNotification = rootView.findViewById<SwitchMaterial>(R.id.switchNotification)
        val btnLogout = rootView.findViewById<Button>(R.id.btnLogout)

        val username = prefManager.getUsername()
        tvProfileName.text = "Welcome, $username!"

        switchDarkMode.isChecked = settingsManager.isDarkMode()
        switchNotification.isChecked = settingsManager.isNotificationEnabled()
        switchFontSize.isChecked = settingsManager.getFontSize() == 18

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            settingsManager.setDarkMode(isChecked)

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        switchFontSize.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                settingsManager.setFontSize(18)
            } else {
                settingsManager.setFontSize(14)
            }

            Toast.makeText(
                requireContext(),
                "Font size diubah! Restart app untuk melihat perubahan.",
                Toast.LENGTH_SHORT
            ).show()
        }

        switchNotification.setOnCheckedChangeListener { _, isChecked ->
            settingsManager.setNotificationEnabled(isChecked)
        }

        btnLogout.setOnClickListener {
            prefManager.logout()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }

        return rootView
    }
}