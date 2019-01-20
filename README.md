# Android
## 課題ブランチ
https://github.com/nyanc0/Android/tree/exercise/aac_livedata

課題を実施する場合は、このブランチから作業用のブランチを切ってください。
作業はForkして行ってください。元のブランチに対してマージなどを勝手に行わないでください。

## 課題内容
## 課題1
LiveDataをつかい、Result<List<TagModel>>をLiveDataを利用してObservable可能なデータに変更してください。

```kt
class TagsViewModel @Inject constructor(
        private val repository: TagRepository,
        private val appSchedulerProvider: SchedulerProvider
) : ViewModel() {

    // 課題1：LiveDataをつかい、Result<List<TagModel>>をLiveDataを利用してObservable可能なデータに変更してください。

    /**
     * タグデータを取得
     *
     * @param nextPage 何ページ目か
     */
    fun loadTagList(nextPage: Int): Flowable<Result<List<TagModel>>> {
        return repository.refreshTags(nextPage, 20, "count")
                .map {
                    Result.success(it)
                }.onErrorReturn { e -> Result.failure(e.message ?: "unknown", e) }
                .observeOn(appSchedulerProvider.ui())
                .startWith(Result.inProgress())
    }
}
```

## 課題2
課題1で作成したLiveDataをobserveしてViewを更新するように変更してください。

```kt
class TagsFragment :
        DaggerFragment() {

    // 省略

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()

        tagsViewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(TagsViewModel::class.java)

        // 課題2：課題1で作成したLiveDataをobserveしてViewを更新するように変更してください。
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

    // 省略
}
```
