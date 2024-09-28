package com.denishrynkevich.photomaptestapp.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.denishrynkevich.photomaptestapp.databinding.FragmentPhotosBinding
import com.denishrynkevich.photomaptestapp.di.viewModel.ViewModelFactory
import javax.inject.Inject

class PhotosFragment : Fragment() {
    private var _binding: FragmentPhotosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val photosViewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)

        _binding = FragmentPhotosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}