package hienle.developer.tpi_mvvm.ui.fragment.attraction_list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hienle.developer.tpi_mvvm.data.usecase.GetAttractionUseCase
import hienle.developer.tpi_mvvm.domain.model.onSuccess
import hienle.developer.tpi_mvvm.domain.model.onThrowable
import hienle.developer.tpi_mvvm.model.AttractionDto
import hienle.developer.tpi_mvvm.model.request.AttractionsRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

/**
 * Created by Hien on 5/9/24
 */
@HiltViewModel
class AttractionListViewModel @Inject constructor(private val getAttractionUseCase: GetAttractionUseCase) : ViewModel
    () {

    val flowLanguage = MutableStateFlow("en")
    val flowPage = MutableStateFlow(1)
    val ldLanguagePage = combine(flowLanguage, flowPage) { lang, page ->
        AttractionsRequest(page, lang)
    }.asLiveData()

    val ldReloadItems = MutableLiveData<List<AttractionDto>>()

    val ldMoreItems = MutableLiveData<List<AttractionDto>>()

    fun getAttractions(request: AttractionsRequest) {
        getAttractionUseCase(request).onSuccess { response ->
            Log.i("AttractionListViewModel", response.total.toString())
            if (request.page == 1) {
                ldReloadItems.postValue(response.data)
            } else {
                ldMoreItems.postValue(response.data)
            }
        }.onThrowable {
            Log.i("AttractionListViewModel", it.localizedMessage ?: "")
        }.launchIn(viewModelScope)
    }

}