package com.dariobabic.githubrepos.core.bindings.radio_group

import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.children
import androidx.databinding.BindingAdapter
import com.dariobabic.githubrepos.R
import com.dariobabic.githubrepos.core.constants.EMPTY_STRING

@BindingAdapter("addRadioGroupCheckListener")
fun addRadioGroupCheckListener(
    view: RadioGroup,
    listener: RadioGroupChangeListener
) {
    view.setOnCheckedChangeListener { _, id ->
        val sortBy = when (id) {
            R.id.radioButtonStars -> "stars"
            R.id.radioButtonForks -> "forks"
            R.id.radioButtonUpdated -> "updated"
            else -> EMPTY_STRING
        }

        listener.onChange(sortBy)
    }
}

@BindingAdapter("enableRadioGroupCheck")
fun enableRadioGroupCheck(
    view: RadioGroup,
    enableRadioGroupCheck: Boolean
) {
    view.children.map { it as RadioButton }.forEach {
        it.isEnabled = !enableRadioGroupCheck
    }

    if (enableRadioGroupCheck) {
        view.clearCheck()
    }
}