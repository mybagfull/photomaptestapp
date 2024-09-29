package com.denishrynkevich.photomaptestapp.ui.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.denishrynkevich.photomaptestapp.databinding.LoadItemBinding

class ImageListLoadStateAdapter: LoadStateAdapter<ImageListLoadStateAdapter.ProgressViewHolder>() {

    override fun onBindViewHolder(holder: ProgressViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ProgressViewHolder {
        val item = LoadItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProgressViewHolder(item)
    }

    class ProgressViewHolder(
        private val binding: LoadItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) = with(binding) {
            progressBar.isVisible = loadState is LoadState.Loading
        }
    }
}