package com.ertbaran.harcamatakip.fragments.edit

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ertbaran.harcamatakip.R
import com.ertbaran.harcamatakip.databinding.FragmentEditScreenBinding
import com.ertbaran.harcamatakip.model.Harcama
import com.ertbaran.harcamatakip.viewmodel.HarcamaViewModel

class EditScreen : Fragment(R.layout.fragment_edit_screen) {

    private val args by navArgs<EditScreenArgs>()
    private lateinit var mHarcamaViewModel:HarcamaViewModel

    private var _binding: FragmentEditScreenBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        mHarcamaViewModel = ViewModelProvider(this).get(HarcamaViewModel::class.java)

        binding.editTextHarcamaAdiUpdate.setText(args.currentHarcama.harcamaAdi)
        binding.editTextHarcamaFiyatiUpdate.setText(args.currentHarcama.harcamaFiyat.toString())
        binding.textViewKur.text = args.currentHarcama.harcamaKuru

        var image = R.drawable.ic_walleticon
        when (args.currentHarcama.harcamaTuru) {
            "Fatura" ->
                image = R.drawable.ic_bill
            "Kira" ->
                image = R.drawable.ic_rent
            "Gıda" ->
                image = R.drawable.ic_food
            "Diğer" ->
                image = R.drawable.ic_other
        }
        binding.imageViewHarcamaUpdate.setImageResource(image)

        binding.buttonHarcamaUpdate.setOnClickListener{
            updateItem()
        }
        binding.buttonHarcamaDelete.setOnClickListener{
            deleteItem()
        }
        binding.buttonBacktoHome.setOnClickListener {
            findNavController().navigate(R.id.action_editScreen_to_homeFragment)
        }
        return view
    }
    private fun updateItem(){
        val harcamaAdi = binding.editTextHarcamaAdiUpdate.text.toString()
        val harcamaFiyati = Integer.parseInt(binding.editTextHarcamaFiyatiUpdate.text.toString())

        if (inputCheck(harcamaAdi,harcamaFiyati)){
            val updatedHarcama = Harcama(args.currentHarcama.id,harcamaAdi,harcamaFiyati,args.currentHarcama.harcamaKuru,args.currentHarcama.harcamaTuru)

            mHarcamaViewModel.harcamaGuncelle(updatedHarcama)
            Toast.makeText(this.context, "Harcama Güncellendi", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_editScreen_to_homeFragment)
        }
        else {
            Toast.makeText(this.context, "Lütfen tüm alanları doldurunuz", Toast.LENGTH_SHORT).show()
        }
    }
    private fun inputCheck(
        harcamaAdi: String,
        harcamaFiyati: Int,
    ): Boolean {
        return !(harcamaAdi == "" || harcamaFiyati == 0)
    }

    private fun deleteItem(){
        mHarcamaViewModel.harcamaSil(args.currentHarcama)
        Toast.makeText(this.context, "Harcama Silindi", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_editScreen_to_homeFragment)
    }
}