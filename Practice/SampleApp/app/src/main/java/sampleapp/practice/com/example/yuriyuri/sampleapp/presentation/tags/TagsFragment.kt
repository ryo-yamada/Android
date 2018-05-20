package sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.tags

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import sampleapp.practice.com.example.yuriyuri.model.TagModel
import sampleapp.practice.com.example.yuriyuri.sampleapp.R
import sampleapp.practice.com.example.yuriyuri.sampleapp.databinding.FragmentTagsBinding
import sampleapp.practice.com.example.yuriyuri.sampleapp.databinding.ItemTagBinding
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.Result
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.common.view.ArrayRecyclerAdapter
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.common.view.BindingHolder
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.common.view.SpaceItemDecoration
import javax.inject.Inject

/**
 * タグ一覧Fragment
 */
class TagsFragment :
        DaggerFragment() {

    /** ViewModel */
    @Inject
    lateinit var tagsViewModel: TagsViewModel

    /** DataBinding */
    private lateinit var binding: FragmentTagsBinding
    /** Adapter */
    private lateinit var tagsAdapter: TagsAdapter
    /** Disposable */
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTagsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        // Tagデータの取得
        tagsViewModel.loadTagList(1)
                .subscribe {
                    when (it) {
                        is Result.Success -> {
                            renderViews(it.data)
                        }
                        is Result.Failure -> {
                            Toast.makeText(this.context, it.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                        is Result.InProgress -> {
                            binding.progress.visibility = View.VISIBLE
                        }
                    }
                }
                .addTo(compositeDisposable)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
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
