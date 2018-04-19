package sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.tags

import io.reactivex.Flowable
import sampleapp.practice.com.example.yuriyuri.model.TagModel
import sampleapp.practice.com.example.yuriyuri.sampleapp.data.repository.TagDataRepository
import sampleapp.practice.com.example.yuriyuri.sampleapp.data.repository.TagRepository
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.Result
import sampleapp.practice.com.example.yuriyuri.sampleapp.util.AppSchedulerProvider
import sampleapp.practice.com.example.yuriyuri.sampleapp.util.SchedulerProvider

/**
 * TagsFragmentのViewModel.</br>
 * TagsFragmentはこのクラスが定義するメソッドからデータ取得を行う
 */
class TagsViewModel {

    private val appSchedulerProvider: SchedulerProvider = AppSchedulerProvider()
    private val repository: TagRepository = TagDataRepository()

    /**
     * タグデータを取得
     *
     * @param nextPage 何ページ目か
     */
    fun loadTagList(nextPage: Int): Flowable<Result<List<TagModel>>>? {

        /*
        * 課題2：
        * TagRepository#refreshTagsを使ってタグ一覧を取得し、
        * 結果に応じたResultを通知するFlowableを返してください.
        * 通知先のスレッドはメインスレッド(UIスレッド)にしてください.
        *
        * TagRepository#refreshTagsのレスポンス型：List<TagModel>
        * TagModel:package sampleapp.practice.com.example.yuriyuri.model.TagModel
        *
        * 通知するデータ：
        * Result：sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.Result
        *
        * 成功：Success(<List<TagModel>>)
        * 失敗：Failure(errorMessage: String, e: Throwable)
        * ローディング中：InProgress()
        * ※ InProgressは元のデータ(List<TagModel>)が通知される前にuiスレッドへ通知されるようにすること.
        *
        * イメージ：
        * データ取得開始 -> Result.inProgress()を通知 -> データ(List<TagModel>)通知
        * */
        // TODO:return <Result<List<TagModel>>>?
        return repository.refreshTags(nextPage, 10, "count")
                .compose { it ->
                    it
                            .map { Result.success(it) }
                            .onErrorReturn { e -> Result.failure(e.message ?: "un known", e) }
                            .observeOn(appSchedulerProvider.ui())
                            .startWith(Result.inProgress())
                }
    }
}
