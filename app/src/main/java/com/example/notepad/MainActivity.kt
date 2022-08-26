package com.example.notepad


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notepad.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), IWordRVAdapter {
    private lateinit var binding: ActivityMainBinding
   private lateinit var viewModel: WordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager= LinearLayoutManager(this)
        val adapter = WordsRVAdapter(this,this)
        binding.recyclerView.adapter = adapter
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(
                application,
            ),
        )[WordViewModel::class.java]

        viewModel.allWords.observe(this, { list->
            list?.let {
                adapter.updateList(it)
            }

        })

    }

    override fun onItemClicked(word: Word) {
  viewModel.delete(word)
    }

    fun submitData(view: View) {
        val wordText = binding.input.text.toString()
        if(wordText.isNotEmpty()){
            viewModel.insert(Word(wordText))
        }
    }
}