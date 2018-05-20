package sampleapp.practice.com.example.yuriyuri.sampleapp.data.repository

import android.support.annotation.CheckResult
import io.reactivex.Flowable
import sampleapp.practice.com.example.yuriyuri.model.TagModel

/**
 * ViewModelが呼び出すRepositoryクラス.<br>
 * ViewModelに返すオブジェクトは、modelパッケージのオブジェクトでなければならない.</br>
 * 実際にどこからとってくるか(api or db)はこのクラスを継承する具象クラスで実装する.
 */
interface TagRepository {

    @CheckResult
    fun refreshTags(next: Int, perPage: Int, sortType: String): Flowable<List<TagModel>>
}
