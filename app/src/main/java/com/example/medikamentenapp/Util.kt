package com.example.medikamentenapp

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.example.medikamentenapp.entities.Medicament

fun formatMeds(nights: List<Medicament>, resources: Resources): Spanned {
        val sb = StringBuilder()
        sb.apply {
            append(resources.getString(R.string.title))
            nights.forEach {
                append("<br>")
                append(it.med_name)
                append(it.med_dosis)
                append(it.med_time1)
                if(it.med_time2 != null)
                {
                    append(it.med_time2)
                }
                if(it.med_time3 != null){
                    append(it.med_time3)}
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
        } else {
            return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

