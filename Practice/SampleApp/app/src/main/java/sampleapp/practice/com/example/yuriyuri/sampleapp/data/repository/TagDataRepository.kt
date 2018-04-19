package sampleapp.practice.com.example.yuriyuri.sampleapp.data.repository

import io.reactivex.Flowable
import sampleapp.practice.com.example.yuriyuri.model.TagId
import sampleapp.practice.com.example.yuriyuri.model.TagModel
import sampleapp.practice.com.example.yuriyuri.sampleapp.data.api.NetworkClient
import sampleapp.practice.com.example.yuriyuri.sampleapp.data.api.QiitaApi
import sampleapp.practice.com.example.yuriyuri.sampleapp.util.AppSchedulerProvider
import sampleapp.practice.com.example.yuriyuri.sampleapp.util.SchedulerProvider

/**
 * タグデータ取得レポジトリ実装クラス.</br>
 * 実際にどこから取得するかはこのクラスのみが知っている.
 */
class TagDataRepository : TagRepository {

    private val netWorkClient: NetworkClient = NetworkClient.instance
    private val qiitaApi: QiitaApi = netWorkClient.provideQiitaApi()
    private val appSchedulerProvider: SchedulerProvider = AppSchedulerProvider()

    override fun refreshTags(next: Int, perPage: Int, sortType: String): Flowable<List<TagModel>> {
        /*
        * 課題1：
        * QiitaApi#getTagsを使ってTag一覧を取得し、TagModelの一覧に変換して通知するFlowableを返却してください.
        *
        * QiitaApi#getTagsのレスポンス型：List<Tag>
        * Tag：sampleapp.practice.com.example.yuriyuri.sampleapp.data.api.response.Tag
        *
        * 通知するデータ型：List<TagModel>
        * TagModel:package sampleapp.practice.com.example.yuriyuri.model.TagModel
        * */
        // TODO:return new Flowable<List<TagModel>>
        return qiitaApi.getTags(next, perPage, sortType).map { it ->
            it.map { it ->
                return@map TagModel(it.followersCount, it.tagIconImageUrl, TagId(it.tagId), it.itemsCount)
            }
        }.subscribeOn(appSchedulerProvider.io())
    }
}