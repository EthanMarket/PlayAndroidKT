package com.ethan.core.api.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.ethan.core.api.R


class LoadingDialog(context: Context) : Dialog(context, R.style.LoadingDialogTheme) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading_view_layout)
    }
}