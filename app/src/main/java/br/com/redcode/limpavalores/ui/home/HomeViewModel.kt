package br.com.redcode.limpavalores.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    val input = MutableLiveData<String>()
    val textUnformatted = MutableLiveData<String>()

    val hasTextUnformatted = MutableLiveData(false)
    val hasContentToPasteFromClipboard = MutableLiveData(false)
    val showAds = MutableLiveData(false)

    fun clearText() = input.value
        ?.takeIf { it.isNotBlank() }
        ?.run {
            replace(" ", "")
            val regex = Regex("[^0-9?!.]")
            val unformatted = replace(regex, "")
            val hasText = unformatted.isNotBlank()

            hasTextUnformatted.postValue(hasText)
            takeIf { hasText }?.also { textUnformatted.postValue(unformatted) }
        }

}