package com.denishrynkevich.photomaptestapp.ui.comments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.denishrynkevich.photomaptestapp.DaggerApp
import com.denishrynkevich.photomaptestapp.databinding.FragmentCommentsBinding
import com.denishrynkevich.photomaptestapp.di.viewModel.ViewModelFactory
import com.denishrynkevich.photomaptestapp.domain.model.CommentOutData
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentsFragment : Fragment(), RecyclerViewCommentItemClickListener {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: CommentsViewModel by viewModels { factory }
    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var commentsListAdapter: CommentsListAdapter
    private val commentsFragmentArgs: CommentsFragmentArgs by navArgs()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as DaggerApp).appComponent.inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getComments(commentsFragmentArgs.id)
        setupRecyclerView()
        observeCommentsData()
        settingButtonSend()
        setImage(commentsFragmentArgs.url)
        binding.tvDate.text = commentsFragmentArgs.date
    }

    private fun setupRecyclerView() {
        commentsListAdapter = CommentsListAdapter(this)
        binding.commentsRecyclerView.apply {
            commentsListAdapter.withLoadStateFooter(CommentsListLoadStateAdapter())
            commentsListAdapter.addLoadStateListener {
                binding.commentsRecyclerView.isVisible = it.refresh != LoadState.Loading
            }
            layoutManager = LinearLayoutManager(context)
            adapter = commentsListAdapter
        }
    }

    private fun observeCommentsData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.commentFlow.collect {
                commentsListAdapter.submitData(it)
            }
        }
    }

    private fun setLongClick(commentOut: CommentOutData): Boolean {
        showDeleteConfirmationDialog(commentOut)
        commentsListAdapter.refresh()
        return true
    }

    private fun showDeleteConfirmationDialog(commentOut: CommentOutData) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirmation of deletion")
            .setMessage("Are you sure you want to delete the comment?")
            .setPositiveButton("Yes") { _, _ ->
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.deleteComment(commentsFragmentArgs.id, commentOut)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun settingButtonSend() {
        binding.sendMessageButton.setOnClickListener {
            try {
                viewModel.sendComment(
                    binding.commentEditText.text.toString(),
                    commentsFragmentArgs.id

                )
                viewModel.getComments(commentsFragmentArgs.id)
                commentsListAdapter.refresh()
            } catch (_: Exception) {
            }
        }
    }

    private fun setImage(url: String) {
        val image = binding.photoImageView
        Glide.with(image)
            .load(url)
            .into(image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemLongClick(item: CommentOutData) = setLongClick(item)

}