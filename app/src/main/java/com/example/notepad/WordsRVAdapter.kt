package com.example.notepad


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordsRVAdapter(private val context: Context, private val listener:IWordRVAdapter): RecyclerView.Adapter<WordsRVAdapter.WordViewHolder>() {
  private val allWords = ArrayList<Word>()
    inner class WordViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.text)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val viewHolder= WordViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_word, parent, false))
        viewHolder.deleteButton.setOnClickListener {
         listener.onItemClicked(allWords[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
   val currentWord = allWords[position]
        holder.textView.text= currentWord.word
    }

    override fun getItemCount(): Int {
        return allWords.size
    }
    fun updateList(newList:List<Word>){
        allWords.clear()
        allWords.addAll(newList)
        notifyDataSetChanged()
    }

}
interface IWordRVAdapter{
    fun onItemClicked(word:Word)
}