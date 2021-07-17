package com.fiiss.mercadolibredev.app

import androidx.fragment.app.Fragment
import com.tapadoo.alerter.Alerter

open class BaseFragment : Fragment() {

    fun alertPanel(text: String?, colorBackground: Int, iconAlert: Int, colorProgress: Int) {
        Alerter.create(requireActivity())
            .setTitle("Ups!")
            .setText(text)
            .setBackgroundColorRes(colorBackground)
            .setIcon(iconAlert)
            .enableSwipeToDismiss()
            .setProgressColorRes(colorProgress)
            .show()
    }
}