package com.example.languagetranslator.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.languagetranslator.databinding.AlphabetInstanceViewholderBinding
import com.example.languagetranslator.databinding.WordInstanceViewholderBinding

class VowelInstanceAdapter(val clickListener: VowelInstanceListener) : ListAdapter<Vowels, RecyclerView.ViewHolder>(EventDiffCallBack()) {

    public lateinit var binding : AlphabetInstanceViewholderBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        binding = AlphabetInstanceViewholderBinding.inflate(layoutInflater, parent, false)
        return VowelInstanceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var vowelInstance: Vowels = getItem(position)
        (holder as VowelInstanceViewHolder).bind(vowelInstance , clickListener)
    }

    class VowelInstanceViewHolder(val binding: AlphabetInstanceViewholderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(vowelInstance: Vowels, clickListener: VowelInstanceListener) {
            binding.vowelInstance = vowelInstance
            binding.executePendingBindings()
        }
    }

    class EventDiffCallBack : DiffUtil.ItemCallback<Vowels>() {
        override fun areItemsTheSame(oldItem: Vowels, newItem: Vowels): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Vowels, newItem: Vowels): Boolean {
            return oldItem == newItem
        }

    }

    class VowelInstanceListener(val clickListener : (eventType: Vowels) -> Unit)
    {
        fun onClick(eventType: Vowels , num : Int) = clickListener(eventType)
    }
}