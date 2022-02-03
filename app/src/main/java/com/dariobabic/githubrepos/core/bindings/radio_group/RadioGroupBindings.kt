package com.dariobabic.githubrepos.core.bindings.radio_group

import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.children
import androidx.databinding.BindingAdapter
import com.dariobabic.githubrepos.R
import com.dariobabic.githubrepos.core.constants.EMPTY_STRING
import com.dariobabic.githubrepos.core.constants.SORT_BY_FORKS
import com.dariobabic.githubrepos.core.constants.SORT_BY_STARS
import com.dariobabic.githubrepos.core.constants.SORT_BY_UPDATED

@BindingAdapter("addRadioGroupCheckListener")
fun addRadioGroupCheckListener(
    view: RadioGroup,
    listener: RadioGroupChangeListener
) {
    view.setOnCheckedChangeListener { _, id ->
        val sortBy = when (id) {
            R.id.radioButtonStars -> SORT_BY_STARS
            R.id.radioButtonForks -> SORT_BY_FORKS
            R.id.radioButtonUpdated -> SORT_BY_UPDATED
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
}

@BindingAdapter("clearRadioGroupCheck")
fun clearRadioGroupCheck(
    view: RadioGroup,
    clearCheck: Boolean
) {
    if (clearCheck) {
        view.clearCheck()
    }
}