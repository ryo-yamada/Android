package sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.tags

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable
import sampleapp.practice.com.example.yuriyuri.model.TagModel
import sampleapp.practice.com.example.yuriyuri.sampleapp.R
import sampleapp.practice.com.example.yuriyuri.sampleapp.databinding.FragmentTagsBinding
import sampleapp.practice.com.example.yuriyuri.sampleapp.databinding.ItemTagBinding
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.Result
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.common.view.ArrayRecyclerAdapter
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.common.view.BindingHolder
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.common.view.SpaceItemDecoration

/**
 * タグ一覧Fragment
 */
class TagsFragment :
        Fragment() {

    /** DataBinding */
    private lateinit var binding: FragmentTagsBinding
    /** Adapter */
    private lateinit var tagsAdapter: TagsAdapter
    /**
     * ViewModel.ViewModelを介してデータを取得する.
     */
    private val tagsViewModel: TagsViewModel = TagsViewModel()
    /** Disposable */
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTagsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        /*
        * 課題3：
        * TagsViewModel#loadTagListを使いタグ一覧(Result<List<TagModel>>)を取得してください.
        * 通知データに応じて、下記の処理を行ってください.
        *
        * ロード開始：binding.progress.visibility = View.VISIBLEを記載し、ローディングプログレスを表示
        * ロード完了(成功)：renderViews(list: List<TagModel>)を呼び出しリストに表示
        * ロード完了(失敗)：トーストでエラーメッセージを表示
        * */
        // TODO:loadTagList and handling tagList
        tagsViewModel.loadTagList(1)!!.subscribe(){
            it ->
            when(it){
                is Result.InProgress -> {it.inProgress}
                is Result.Success -> {
                    renderViews(it.data)
                }
                is Result.Failure ->{
                    Log.d("Failure", it.errorMessage)
                }
            }
        }

    }

    /**
     * RecyclerViewのセットアップ
     */
    private fun setupRecyclerView() {
        binding.recyclerView.addItemDecoration(SpaceItemDecoration(8))
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
    }

    /**
     * View描画
     */
    private fun renderViews(list: List<TagModel>) {
        if (binding.recyclerView.adapter == null) {
            tagsAdapter = TagsAdapter((ArrayList(list)))
            binding.recyclerView.adapter = tagsAdapter
        } else {
            tagsAdapter.reset(list)
        }
        binding.progress.visibility = View.GONE
    }

    /**
     * RecyclerViewAdapter
     */
    inner class TagsAdapter(list: ArrayList<TagModel>)
        : ArrayRecyclerAdapter<TagModel, BindingHolder<ItemTagBinding>>(list) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<ItemTagBinding> {
            return BindingHolder(parent.context, parent, R.layout.item_tag)
        }

        override fun onBindViewHolder(holder: BindingHolder<ItemTagBinding>, position: Int) {
            val data = list[position]
            holder.binding!!.tag = data
        }
    }

    companion object {
        fun newInstance(): TagsFragment = TagsFragment()
        val TAG = TagsFragment::class.java.canonicalName!!
    }
}
