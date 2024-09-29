package com.denishrynkevich.photomaptestapp.ui.photos

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.denishrynkevich.photomaptestapp.databinding.ItemPhotoBinding
import com.denishrynkevich.photomaptestapp.domain.model.ImageOutData

class ImageListHolder(
    private val binding: ItemPhotoBinding,
    private val itemClickListener: RecyclerViewItemClickListener
) : ViewHolder(binding.root) {

    fun onBind(item: ImageOutData) {
        setDate(item)
        setImage(item.url, binding.photoImageView)
        setClick(item)
    }

    private fun setClick(item: ImageOutData) {
        itemView.setOnClickListener {
            itemClickListener.onItemClick(item)
        }
        itemView.setOnLongClickListener {
            itemClickListener.onItemLongClick(item)
        }
    }

    private fun setDate(item: ImageOutData) {
        binding.dateTextView.text = item.date
    }

    private fun setImage(url: String, image: ImageView) {
        Glide.with(image)
            .load(url)
            .into(image)
    }
}

interface RecyclerViewItemClickListener {
    fun onItemClick(item: ImageOutData)
    fun onItemLongClick(item: ImageOutData): Boolean
}
