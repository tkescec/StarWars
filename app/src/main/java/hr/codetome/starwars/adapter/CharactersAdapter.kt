package hr.codetome.starwars.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hr.codetome.starwars.databinding.CharactersRowBinding
import hr.codetome.starwars.model.Character

class CharactersAdapter(private val onClickListener: OnClickListener) :
    PagingDataAdapter<Character, CharactersAdapter.MyViewHolder>(CHARACTER_COMPARATOR) {

    inner class MyViewHolder(private val binding: CharactersRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character?) {
            binding.nameTextView.text = character?.name
            binding.dateTextView.text = character?.birthYear
            binding.genderTextView.text = character?.gender
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CharactersRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(character!!)
        }
    }

    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }

    class OnClickListener(val clickListener: (character: Character) -> Unit) {
        fun onClick(character: Character) = clickListener(character)
    }
}