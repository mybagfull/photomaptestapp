package com.denishrynkevich.photomaptestapp.ui.photos

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.denishrynkevich.photomaptestapp.DaggerApp
import com.denishrynkevich.photomaptestapp.databinding.FragmentPhotoBinding
import com.denishrynkevich.photomaptestapp.di.viewModel.ViewModelFactory
import com.denishrynkevich.photomaptestapp.domain.model.ImageOutData
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotoFragment : Fragment(), RecyclerViewItemClickListener {

    @Inject
    lateinit var factory: ViewModelFactory
    private val photoViewModel: PhotoViewModel by viewModels { factory }
    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageListAdapter: ImageListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as DaggerApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoViewModel.updateList()
        setupRecyclerView()
        observeImage()
        observeDeleteImageLiveData()
    }

    private fun showDeleteConfirmationDialog(imageOut: ImageOutData) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirmation of deletion")
            .setMessage("Are you sure you want to delete the image?")
            .setPositiveButton("Yes") { _, _ ->
                viewLifecycleOwner.lifecycleScope.launch {
                    photoViewModel.deleteImage(imageOut.id, imageOut)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun setupRecyclerView() {
        imageListAdapter = ImageListAdapter(this)
        binding.photoRecyclerView.apply {
            imageListAdapter.withLoadStateFooter(ImageListLoadStateAdapter())
            imageListAdapter.addLoadStateListener {
                binding.photoRecyclerView.isVisible = it.refresh != LoadState.Loading
            }
            layoutManager = GridLayoutManager(context, 3)
            adapter = imageListAdapter
        }
    }


    private fun observeImage() {
        viewLifecycleOwner.lifecycleScope.launch {
            photoViewModel.imageFlow.collect {
                imageListAdapter.submitData(it)
            }
        }
    }

    private fun observeDeleteImageLiveData() {
        photoViewModel.deleteImageLiveData.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                Toast
                    .makeText(context, "The photo was successfully deleted", Toast.LENGTH_SHORT)
                    .show()
                imageListAdapter.refresh()
            } else {
                Toast
                    .makeText(context, "The photo has not been deleted", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onItemLongClick(item: ImageOutData): Boolean {
        showDeleteConfirmationDialog(item)
        return true
    }

    override fun onItemClick(item: ImageOutData) {
        val action =
            PhotoFragmentDirections.actionNavPhotosToCommentsFragment(item.id, item.url, item.date)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}