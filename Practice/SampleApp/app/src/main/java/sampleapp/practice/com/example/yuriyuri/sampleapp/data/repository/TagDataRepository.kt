package sampleapp.practice.com.example.yuriyuri.sampleapp.data.repository

import io.reactivex.Flowable
import sampleapp.practice.com.example.yuriyuri.model.TagId
import sampleapp.practice.com.example.yuriyuri.model.TagModel
import sampleapp.practice.com.example.yuriyuri.sampleapp.data.api.QiitaApi
import sampleapp.practice.com.example.yuriyuri.sampleapp.util.SchedulerProvider
import javax.inject.Inject

/**
 * タグデータ取得レポジトリ実装クラス.</br>
 * 実際にどこから取得するかはこのクラスのみが知っている.
 */
// ここに引数ありのコンストラクタでInjectできるようにする
class TagDataRepository @Inject constructor(
        private val qiitaApi: QiitaApi,
        private val schedulerProvider: SchedulerProvider
): TagRepository {

    // 課題：
    // NetworkClientクラスを削除し、QiitaApiとSchedulerProviderを
    // TagDataRepositoryのコンストラクタでInjectして渡してください.
    // SchedulerProviderはすでにAppModuleで指定され、Injectできるようになっています.

    override fun refreshTags(next: Int, perPage: Int, sortType: String): Flowable<List<TagModel>> {
        return qiitaApi.getTags(next, perPage, sortType)
                .map {
                    // response:List<Tag>
                    response
                    ->
                    response.map {
                        // it : Tag
                        it ->
                        TagModel(it.followersCount,
                                it.tagIconImageUrl,
                                TagId(it.tagId),
                                it.itemsCount)
                    }
                }
                .subscribeOn(schedulerProvider.io())
    }
}