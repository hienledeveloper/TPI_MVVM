package hienle.developer.tpi_mvvm.ui.fragment.attraction_list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hienle.developer.tpi_mvvm.core.preference.UserSharedPreferences
import hienle.developer.tpi_mvvm.data.usecase.GetAttractionUseCase
import hienle.developer.tpi_mvvm.domain.model.onSuccess
import hienle.developer.tpi_mvvm.domain.model.onThrowable
import hienle.developer.tpi_mvvm.model.AttractionDto
import hienle.developer.tpi_mvvm.model.request.AttractionsRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

/**
 * Created by Hien on 5/9/24
 */
@HiltViewModel
class AttractionListViewModel @Inject constructor(
    private val getAttractionUseCase: GetAttractionUseCase,
    private val userSharedPreferences: UserSharedPreferences
) :
    ViewModel() {

    val flowLanguage = MutableStateFlow(userSharedPreferences.getLanguage())
    val flowPage = MutableStateFlow(1)
    private val flowRefresh = MutableStateFlow(false)
    val ldLanguagePage = combine(flowRefresh, flowPage) { _, page ->
        AttractionsRequest(page, flowLanguage.value)
    }.asLiveData()

    val ldReloadItems = MutableLiveData<List<AttractionDto>>()
    val ldMoreItems = MutableLiveData<List<AttractionDto>>()
    val ldCompleted = MutableLiveData<Boolean>(false)


    fun getAttractions(request: AttractionsRequest) {
        ldCompleted.value = false
        getAttractionUseCase(request).onSuccess { response ->
            Log.i("AttractionListViewModel", response.total.toString())
            if (request.page == 1) {
                ldReloadItems.postValue(response.data)
            } else {
                ldMoreItems.postValue(response.data)
            }
        }.onThrowable {
            Log.i("AttractionListViewModel", it.localizedMessage ?: "")
        }.onCompletion {
            ldCompleted.value = true
        }.launchIn(viewModelScope)
    }

    fun fetch() {
        if (flowPage.value > 1) {
            flowPage.value = 1
        } else {
            flowRefresh.value = !flowRefresh.value
        }
    }

    fun loadMore() {
        if (ldCompleted.value == true) {
            flowPage.value += 1
        }
    }

}