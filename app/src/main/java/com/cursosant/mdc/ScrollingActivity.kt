package com.cursosant.mdc

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.URLUtil
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

        loadImage("https://pamipe.com/wiki/wp-content/uploads/2022/09/Dogo-de-Burdeos-2-1-800x780.jpg")

        binding.content.cbEnablePassword.setOnClickListener {
            binding.content.tilPassword.isEnabled = !binding.content.tilPassword.isEnabled
        }

        binding.content.etUrl.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
            if (!b) {
                var errorMessage: String? = null
                val url = binding.content.etUrl.text.toString()
                if (url.isEmpty()) {
                    errorMessage = getString(R.string.required_field)
                } else if (URLUtil.isValidUrl(url)) {
                    loadImage(url)
                } else {
                    errorMessage = getString(R.string.invalid_url)
                }
                binding.content.tilUrl.error = errorMessage
            }
        }

        binding.content.toggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (!isChecked) {
                binding.content.root.setBackgroundColor(Color.WHITE)
            } else {
                binding.content.root.setBackgroundColor(
                    when(checkedId) {
                        R.id.btn_red -> Color.RED
                        R.id.btn_green -> Color.GREEN
                        R.id.btn_blue -> Color.BLUE
                        else -> Color.WHITE
                    }
                )
            }
        }
    }

    private fun loadImage(url: String) {
        Glide.with(this)
            .load(url)
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