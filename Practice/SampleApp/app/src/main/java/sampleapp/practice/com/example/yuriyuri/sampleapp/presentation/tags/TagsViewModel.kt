package sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.tags

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Flowable
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

//    /**
//     * タグデータを取得
//     *
//     * @param nextPage 何ページ目か
//     */
//    fun loadTagList(nextPage: Int): Flowable<Result<List<TagModel>>> {
//        return repository.refreshTags(nextPage, 20, "count")
//                .map {
//                    Result.success(it)
//                }.onErrorReturn { e -> Result.failure(e.message ?: "unknown", e) }
//                .observeOn(appSchedulerProvider.ui())
//                .startWith(Result.inProgress())
//    }
//
    /**
     * タグデータを取得
     *
     * @param nextPage 何ページ目か
     */
    fun loadTagList(nextPage: Int): LiveData<Result<List<TagModel>>> {
                repository.refreshTags(nextPage, 20, "count")
                .map {
                    mutableState.postValue(Result.success(it))
                }.observeOn(appSchedulerProvider.ui())

        return  mutableState
    }
}