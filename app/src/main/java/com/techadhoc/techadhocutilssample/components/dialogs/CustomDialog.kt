package com.techadhoc.techadhocutilssample.components.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.techadhoc.techadhocutilssample.R

class CustomDialog : DialogFragment() {
    private var onClick: () -> Unit = { }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.error_dialog_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        val login = view.findViewById<Button>(R.id.login)
        login.setOnClickListener {
            doClick()
        }
    }

    private fun doClick() {
        this.onClick.invoke()
        dismiss()
    }

    override fun getTheme(): Int = R.style.DialogTheme

    fun show(fragmentManager: FragmentManager) {
        val fragTrans = fragmentManager.beginTransaction()
        val prevFrag = fragmentManager.findFragmentByTag(Dialog_TAG)
        if (null != prevFrag) fragTrans.remove(prevFrag)
        fragTrans.addToBackStack(null)
        show(fragTrans, Dialog_TAG)
    }

    companion object {
        private const val Dialog_TAG = "error_dialog"
        fun newInstance(
            onClick: () -> Unit
        ): CustomDialog = CustomDialog().apply {
            this.onClick = onClick
        }
    }
}

