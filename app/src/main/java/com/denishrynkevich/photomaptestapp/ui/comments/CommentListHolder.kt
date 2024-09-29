package com.denishrynkevich.photomaptestapp.ui.comments

import androidx.recyclerview.widget.RecyclerView
import com.denishrynkevich.photomaptestapp.databinding.ItemCommentBinding
import com.denishrynkevich.photomaptestapp.domain.model.CommentOutData
import com.denishrynkevich.photomaptestapp.ui.photos.RecyclerViewItemClickListener
import java.text.SimpleDateFormat
import java.util.Locale

class CommentListHolder(
    private val binding: ItemCommentBinding,
    private val itemClickListener: RecyclerViewCommentItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: CommentOutData) {
        setDate(item.date)
        setText(item.text)
        setClick(item)
    }

    private fun setText(text: String) {
        binding.commentTextView.text = text
    }

    private fun setClick(item: CommentOutData) {
        itemView.setOnLongClickListener {
            itemClickListener.onItemLongClick(item)
        }
    }

    private fun setDate(date: String) {
        binding.commentDateTextView.text = getDate(date)
    }

    private fun getDate(date: String): String {
        val formatter = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.getDefault())
        return formatter.format(date.toLong() * 1000)

    }

}

interface RecyclerViewCommentItemClickListener {
    fun onItemLongClick(item: CommentOutData): Boolean
}