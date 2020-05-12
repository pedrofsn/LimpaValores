package br.com.redcode.limpavalores.domain

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.google.firebase.analytics.FirebaseAnalytics

/*
    CREATED BY @PEDROFSN IN 01/05/20 15:33
*/

fun <V : ViewDataBinding> LayoutInflater.getBinding(
    layout: Int,
    container: ViewGroup?
): ViewDataBinding? = DataBindingUtil.inflate<V>(this, layout, container, false)

fun ViewDataBinding.setupMVVM(
    variableId: Int,
    vm: ViewModel,
    owner: LifecycleOwner
): ViewDataBinding {
    lifecycleOwner = owner
    setVariable(variableId, vm)
    return this
}

fun Fragment.logEvent(event: String) {
    logEvent(event, Bundle())
}

fun Fragment.logEvent(event: String, bundle: Bundle) {
    FirebaseAnalytics.getInstance(activity as Context).logEvent(event, bundle)
}