package com.cursosant.mdc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cursosant.mdc.databinding.ActivityScrollingBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.snackbar.Snackbar

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            if (binding.bottomAppBar.fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            } else {
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            }
        }

        binding.bottomAppBar.setNavigationOnClickListener {
            Snackbar.make(binding.root, getString(R.string.successful_event), Snackbar.LENGTH_LONG)
                .setAnchorView(binding.fab)
                .show()
        }

        binding.content.btnSkip.setOnClickListener {
            binding.content.cvAd.visibility = View.GONE
        }

        binding.content.btnBuy.setOnClickListener {
            Snackbar.make(it, getString(R.string.purchased), Snackbar.LENGTH_LONG)
                .setAnchorView(binding.fab)
                .setAction(getString(R.string.go), {
                    Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show()
                })
                .show()
        }

        Glide.with(this)
            .load("https://pamipe.com/wiki/wp-content/uploads/2022/09/Dogo-de-Burdeos-2-1-800x780.jpg")
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.content.imgCover)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when(item.itemId){
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}