package com.denishrynkevich.photomaptestapp.ui.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.denishrynkevich.photomaptestapp.databinding.LoadItemBinding

class CommentsListLoadStateAdapter: LoadStateAdapter<CommentsListLoadStateAdapter.ProgressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ProgressViewHolder {
        val item = LoadItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProgressViewHolder(item)
    }

    override fun onBindViewHolder(holder: ProgressViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class ProgressViewHolder(
        private val binding: LoadItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) = with(binding) {
            progressBar.isVisible = loadState is LoadState.Loading
        }
    }
}