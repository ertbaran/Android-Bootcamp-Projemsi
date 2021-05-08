package com.ertbaran.harcamatakip.fragments.add

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ertbaran.harcamatakip.R
import com.ertbaran.harcamatakip.model.Harcama
import com.ertbaran.harcamatakip.viewmodel.HarcamaViewModel
import com.ertbaran.harcamatakip.databinding.FragmentAddBinding


class AddScreen : Fragment(R.layout.fragment_add) {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var mHarcamaViewModel: HarcamaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root

        mHarcamaViewModel = ViewModelProvider(this).get(HarcamaViewModel::class.java)

        binding.buttonHarcamaEkle.setOnClickListener {
            insertDataToDatabase()
        }
        binding.buttonBacktoHome.setOnClickListener{
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)
        }

        return view
    }

    private fun insertDataToDatabase() {
        val harcamaAdi = binding.editTextHarcamaAdi.text.toString()
        var harcamaFiyati = binding.editTextHarcamaFiyati.text.toString()
        val harcamaKuruID = binding.radioGroupHarcamaKuru.checkedRadioButtonId.toString()
        val harcamaTuruID = binding.radioGroupHarcamaTuru.checkedRadioButtonId.toString()

        var harcamaKuru = ""
        var harcamaTuru = ""

        if (harcamaFiyati == "") harcamaFiyati = "0"

        when (harcamaKuruID) {
            R.id.radioTL.toString() ->
                harcamaKuru = "₺"
            R.id.radioDolar.toString() ->
                harcamaKuru = "$"
            R.id.radioEuro.toString() ->
                harcamaKuru = "€"
            R.id.radioSterlin.toString() ->
                harcamaKuru = "£"
            R.id.radioBtc.toString() ->
                harcamaKuru = "₿"
            "-1" ->
                harcamaKuru = "-1"
        }


        when (harcamaTuruID) {
            R.id.radioFatura.toString() ->
                harcamaTuru = "Fatura"
            R.id.radioKira.toString() ->
                harcamaTuru = "Kira"
            R.id.radioGida.toString() ->
                harcamaTuru = "Gıda"
            R.id.radioDiger.toString() ->
                harcamaTuru = "Diğer"
            "-1" ->
                harcamaTuru = "-1"
        }

        if (inputCheck(harcamaAdi, harcamaFiyati, harcamaKuru, harcamaTuru)) {
            // Bir harcama nesnesi oluştur
            val harcama = Harcama(
                0,
                harcamaAdi,
                Integer.parseInt(harcamaFiyati),
                harcamaKuru,
                harcamaTuru
            )
            
            // Harcamayı veritabanına gekle
            mHarcamaViewModel.harcamaEkle(harcama)

            Toast.makeText(this.context, "Harcama eklendi", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_homeFragment)
        } else {
            Toast.makeText(this.context, "Lütfen tüm alanları doldurunuz", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(
        harcamaAdi: String,
        harcamaFiyati: String,
        harcamaKuru: String,
        harcamaTuru: String
    ): Boolean {
        return !(harcamaAdi == "" || harcamaFiyati == "0" || harcamaKuru == "-1" || harcamaTuru == "-1")
    }
}