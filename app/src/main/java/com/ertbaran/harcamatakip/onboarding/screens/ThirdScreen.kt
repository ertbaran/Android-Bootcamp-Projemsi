package com.ertbaran.harcamatakip.onboarding.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ertbaran.harcamatakip.R
import com.ertbaran.harcamatakip.databinding.FragmentThirdScreenBinding

class ThirdScreen : Fragment(R.layout.fragment_third_screen) {
    private var _binding: FragmentThirdScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.textFinish.setOnClickListener{
            findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
        onBoardingFinish()
        }
        return view
    }
    private fun onBoardingFinish(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished",true)
        editor.apply()
    }
}