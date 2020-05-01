package br.com.redcode.limpavalores.ui.home

import android.content.ClipboardManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.redcode.limpavalores.BR
import br.com.redcode.limpavalores.MainActivity
import br.com.redcode.limpavalores.R
import br.com.redcode.limpavalores.databinding.FragmentHomeBinding
import br.com.redcode.limpavalores.domain.getBinding
import br.com.redcode.limpavalores.domain.setupMVVM

class HomeFragment : Fragment(), ClipboardManager.OnPrimaryClipChangedListener {

    private val viewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }

    private val imageViewPaste by lazy { view?.findViewById<ImageView>(R.id.imageViewPaste) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.getBinding<FragmentHomeBinding>(R.layout.fragment_home, container)
            ?.setupMVVM(variableId = BR.viewModel, vm = viewModel, owner = this)
            ?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageViewPaste?.setOnClickListener { pasteText() }
    }

    private fun getClipboardManager() = (activity as MainActivity).clipboardManager

    override fun onResume() {
        super.onResume()
        getClipboardManager()?.addPrimaryClipChangedListener(this)
    }

    override fun onPause() {
        getClipboardManager()?.removePrimaryClipChangedListener(this)
        super.onPause()
    }

    private fun pasteText() = getData()
        ?.takeIf { it.isNotBlank() }
        ?.let { viewModel.input.postValue(it) }

    private fun getData() = getClipboardManager()
        ?.takeIf { it.hasPrimaryClip() }
        ?.primaryClip
        ?.getItemAt(0)
        ?.coerceToText(activity)
        ?.toString()
        ?.trim()

    private fun hasData() = getData()
        ?.takeIf { getClipboardManager()?.hasPrimaryClip() == true }
        ?.takeIf { it.isNotBlank() }
        ?.run { true } ?: false

    override fun onPrimaryClipChanged() {
        viewModel.hasContentToPasteFromClipboard.postValue(hasData())
    }

}