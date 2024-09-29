package com.denishrynkevich.photomaptestapp.ui.photos

import com.denishrynkevich.photomaptestapp.databinding.ItemPhotoBinding
import com.denishrynkevich.photomaptestapp.domain.model.ImageOutData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter

class ImageListAdapter(
    private val itemClickListener: RecyclerViewItemClickListener
) : PagingDataAdapter<ImageOutData, ImageListHolder>(ImageListDiffUtil()) {

    override fun onBindViewHolder(holder: ImageListHolder, position: Int) {
        val item = getItem(position) ?: return
        item.let {
            holder.onBind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = ItemPhotoBinding.inflate(inflater)
        return ImageListHolder(item, itemClickListener)
    }

    override fun onBindViewHolder(
        holder: ImageListHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val item = payloads[0] as ImageOutData
            holder.onBind(item)
        }

    }
}