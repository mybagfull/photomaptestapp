package com.denishrynkevich.photomaptestapp.ui.photos

import androidx.recyclerview.widget.DiffUtil
import com.denishrynkevich.photomaptestapp.domain.model.ImageOutData

class ImageListDiffUtil : DiffUtil.ItemCallback<ImageOutData>() {

    override fun areItemsTheSame(oldItem: ImageOutData, newItem: ImageOutData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageOutData, newItem: ImageOutData): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: ImageOutData, newItem: ImageOutData): Any? {
        if (oldItem != newItem) {
            return newItem
        }
        return super.getChangePayload(oldItem, newItem)
    }
}