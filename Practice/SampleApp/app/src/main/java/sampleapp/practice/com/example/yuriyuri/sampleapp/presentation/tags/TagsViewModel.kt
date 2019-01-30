package sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.tags

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.rxkotlin.subscribeBy
import sampleapp.practice.com.example.yuriyuri.model.TagModel
import sampleapp.practice.com.example.yuriyuri.sampleapp.data.repository.TagRepository
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.Result
import sampleapp.practice.com.example.yuriyuri.sampleapp.util.SchedulerProvider
import javax.inject.Inject

/**
 * TagsFragmentのViewModel.</br>
 * TagsFragmentはこのクラスが定義するメソッドからデータ取得を行う
 */
class TagsViewModel @Inject constructor(
        private val repository: TagRepository,
        private val appSchedulerProvider: SchedulerProvider
) : ViewModel() {

    // 課題1：LiveDataをつかい、Result<List<TagModel>>をLiveDataを利用してObservable可能なデータに変更してください。
    private val mutableState: MutableLiveData<Result<List<TagModel>>> = MutableLiveData()
    val refreshResult: LiveData<Result<List<TagModel>>> = mutableState

    init {
        loadTagList(1)
    }
    /**
     * タグデータを取得
     *
     * @param nextPage 何ページ目か
     */
    private fun loadTagList(nextPage: Int) {
        repository.refreshTags(nextPage, 20, "count")
                .subscribeOn(appSchedulerProvider.io())
                .subscribeBy(
                        onNext = {
                            mutableState.postValue(Result.success(it))
                        },
                        onError = { e ->
                            Result.failure<List<TagModel>>(e.message ?: "unknown", e)
                        }
                )
    }

}