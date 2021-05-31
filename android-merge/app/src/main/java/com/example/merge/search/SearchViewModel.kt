package com.example.merge.search

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.merge.entities.ProductInfo
import com.example.merge.livedata.SingleLiveEvent
import com.example.merge.network.ProductInfoDTO
import com.example.merge.network.RetrofitInstanceFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.stream.Collectors

class SearchViewModel : ViewModel() {

    val keywordField = ObservableField("")

    val goBackEvent = SingleLiveEvent<Unit>()
    val requestFailedEvent = SingleLiveEvent<Throwable>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _searchResults = MutableLiveData<List<ProductInfo>>()
    val searchResults: LiveData<List<ProductInfo>> = _searchResults

    private var lastRequest: Call<*>? = null

    init {
        // 입력값이 바뀔 때마다 검색!
        // *주의 : EditText의 onTextChanged를 사용하면 입력값이 한 단계씩 늦게 업데이트되는 현상 발생!
        keywordField.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                search()
            }
        })
    }

    fun goBack() {
        goBackEvent.call()
    }

    private fun search() {
        val keyword = keywordField.get() ?: return

        if (keyword.isBlank()) {
            // 키워드가 읎으면 무시!
            _searchResults.value = listOf()
            return
        }

        _isLoading.value = true

        // 이전 요청이 취소되지 않았으면 취소하기!
        lastRequest?.takeIf { !it.isCanceled }?.cancel()

        val call = RetrofitInstanceFactory.getInstance().getProducts(keyword)

        call.enqueue(object : Callback<List<ProductInfoDTO>> {
            override fun onResponse(
                call: Call<List<ProductInfoDTO>>,
                response: Response<List<ProductInfoDTO>>
            ) {
                if (response.isSuccessful) {
                    val products = response.body()!!.stream()
                        .map { obj: ProductInfoDTO -> obj.toProductInfo() }
                        .collect(Collectors.toList())

                    _searchResults.value = products
                    _isLoading.value = false

                } else {
                    requestFailedEvent.call()
                }

                _isLoading.value = false
            }

            override fun onFailure(call: Call<List<ProductInfoDTO>>, t: Throwable) {
                // 요청 취소되었을 때에 뜨는 오류:
                // HTTP FAILED: java.io.IOException: Canceled or HTTP FAILED: java.net.SocketException: Socket closed

                // 해당 경우에는 군말없이 넘어가주자 ㅎㅎ
                when (t.message) {
                    "Canceled" -> return
                    "Socket closed" -> return
                }

                t.printStackTrace()
                requestFailedEvent.value = t
                _isLoading.value = false
            }
        })

        // 이 요청을 언젠가 취소해야 할 수 있기 때문에 저장해둠!
        lastRequest = call
    }
}