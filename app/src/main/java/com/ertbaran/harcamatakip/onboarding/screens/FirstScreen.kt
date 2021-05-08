package com.ertbaran.harcamatakip.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.ertbaran.harcamatakip.R
import com.ertbaran.harcamatakip.databinding.FragmentFirstScreenBinding

class FirstScreen : Fragment(R.layout.fragment_first_screen) {
    private var _binding: FragmentFirstScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.textNext1.setOnClickListener{
            viewPager?.currentItem = 1
        }
        return view
    }
}