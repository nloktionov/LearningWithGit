package com.example.learningwithgit

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.learningwithgit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var sPref: SharedPreferences
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLoad.setOnClickListener(this)
        binding.buttonSave.setOnClickListener(this)

        loadText()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.buttonLoad.id -> {
                loadText()
            }
            binding.buttonSave.id -> {
                saveText()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        saveText()
    }



    private fun saveText() {
        sPref = getPreferences(MODE_PRIVATE)
        val ed = sPref.edit()
        ed.putString("saved_text", binding.EditText.text.toString())
        ed.apply()
        Toast.makeText(this, "text saved!", Toast.LENGTH_SHORT).show()
    }

    private fun loadText() {
        sPref = getPreferences(MODE_PRIVATE)
        var savedText = sPref.getString("saved_text", "")
        binding.EditText.setText(savedText)
        Toast.makeText(this, "text loaded!", Toast.LENGTH_SHORT).show()
    }
}