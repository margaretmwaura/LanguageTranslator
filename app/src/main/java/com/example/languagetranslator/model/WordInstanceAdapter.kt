package com.example.languagetranslator.model

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.languagetranslator.databinding.WordInstanceViewholderBinding

class WordInstanceAdapter(val clickListener: WordInstanceListener) : ListAdapter<WordInstance,RecyclerView.ViewHolder>(EventDiffCallBack()) {

    public lateinit var binding : WordInstanceViewholderBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        binding = WordInstanceViewholderBinding.inflate(layoutInflater, parent, false)
        return WordInstanceeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var wordInstance: WordInstance = getItem(position)
        (holder as WordInstanceeViewHolder).bind(wordInstance , clickListener)
    }

    class WordInstanceeViewHolder(val binding: WordInstanceViewholderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(eventType: WordInstance, clickListener: WordInstanceListener) {
            binding.executePendingBindings()
        }
    }

    class EventDiffCallBack : DiffUtil.ItemCallback<WordInstance>() {
        override fun areItemsTheSame(oldItem: WordInstance, newItem: WordInstance): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WordInstance, newItem: WordInstance): Boolean {
            return oldItem == newItem
        }

    }

    class WordInstanceListener(val clickListener : (eventType: WordInstance) -> Unit)
    {
        fun onClick(eventType: WordInstance , num : Int) = clickListener(eventType)
    }
}