package br.com.redcode.limpavalores.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

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

