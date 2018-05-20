package sampleapp.practice.com.example.yuriyuri.sampleapp.presentation

import android.databinding.DataBindingUtil
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import sampleapp.practice.com.example.yuriyuri.sampleapp.R
import sampleapp.practice.com.example.yuriyuri.sampleapp.databinding.ActivityMainBinding
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.tags.TagsFragment

class MainActivity : DaggerAppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        initFragment()
    }

    private fun initFragment() {
        val manager = supportFragmentManager
        var tagsFragment = manager.findFragmentByTag(TagsFragment.TAG)

        if (tagsFragment == null) {
            tagsFragment = TagsFragment.newInstance()
        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, tagsFragment, TagsFragment.TAG)
        transaction.commit()
    }
}
