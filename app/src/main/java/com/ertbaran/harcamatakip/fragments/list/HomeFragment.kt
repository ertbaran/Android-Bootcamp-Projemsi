package com.ertbaran.harcamatakip.fragments.list

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ertbaran.harcamatakip.MainViewModel
import com.ertbaran.harcamatakip.MainViewModelFactory
import com.ertbaran.harcamatakip.R
import com.ertbaran.harcamatakip.RecyclerAdapter
import com.ertbaran.harcamatakip.viewmodel.HarcamaViewModel
import com.ertbaran.harcamatakip.databinding.FragmentHomeBinding
import com.ertbaran.harcamatakip.repository.Repository


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mHarcamaViewModel: HarcamaViewModel
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        var sum:Int

        // RETROFIT START
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()

        viewModel.myResponse.observe(requireActivity(), Observer { response ->

            if (response.isSuccessful){
                binding.textViewMerhaba.text = response.body()?.date
            }
            else{
                Toast.makeText(this.context,"Güncel kur alınamadı\n("+response.code().toString() +" hatası)", Toast.LENGTH_SHORT).show()
            }
        })
        // RETROFIT END

        // RecyclerView
        val adapter = RecyclerAdapter()
        val recyclerView = binding.recyclerViewHarcama
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // HarcamaViewModel
        mHarcamaViewModel = ViewModelProvider(this).get(HarcamaViewModel::class.java)
        mHarcamaViewModel.readAllData.observe(viewLifecycleOwner, Observer {harcama->
            adapter.setData(harcama)
        })
        binding.addButton.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
        binding.textViewKullaniciAdi.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_userInfoScreen)
        }

        getUserInfo()
        return view
    }

    private fun getUserInfo() {
        val sharedPref = requireActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val userName = sharedPref.getString("userName","")
        val userGender = sharedPref.getString("userGender","")
        var hitabet = ""

        when (userGender) {
            R.id.radioGenderKadin.toString() ->
                hitabet = "Hanım"
            R.id.radioGenderErkek.toString() ->
                hitabet = "Bey"
            R.id.radioGenderBelirtilmedi.toString() ->
                hitabet = ""
        }
        if (userName!="")
        binding.textViewKullaniciAdi.text = userName + " " + hitabet
    }
}