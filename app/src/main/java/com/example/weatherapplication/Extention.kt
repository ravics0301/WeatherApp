package com.example.weatherapplication

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.util.*

fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}
const val WEATHER_API_KEY = "9271cfbe80a2610d5d4d2ef328f1f89d"

fun Context.getProgressDialog(): ProgressDialog {
    return ProgressDialog(this).apply {
        setCancelable(false)
        setMessage("Please Wait...")
    }
}




fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
