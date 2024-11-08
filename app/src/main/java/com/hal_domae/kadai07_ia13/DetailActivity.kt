package com.hal_domae.kadai07_ia13

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hal_domae.kadai07_ia13.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // MainActivityからPOSITIONを受け取る
        val position = intent.getIntExtra("POSITION", 0)

        val data = listData[position]

        binding.detailImage.setImageResource(data["image"].toString().toInt())
        binding.detailTitle.text = data["name"].toString()
        binding.detailText.text = data["explain"].toString()

        binding.likeButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_SUBJECT,
                    "${data["name"].toString()}の図鑑")
                putExtra(Intent.EXTRA_TEXT,
                    "モンスターの名前 : ${data["name"].toString()}\nモンスターの詳細\n${data["explain"].toString()}")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}