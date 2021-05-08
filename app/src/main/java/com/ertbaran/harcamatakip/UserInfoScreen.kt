package com.ertbaran.harcamatakip

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ertbaran.harcamatakip.databinding.FragmentUserInfoScreenBinding

class UserInfoScreen : Fragment(R.layout.fragment_user_info_screen) {

    private var _binding: FragmentUserInfoScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserInfoScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        val sharedPref = requireActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val userName = sharedPref.getString("userName","")
        val userGenderID = sharedPref.getString("userGender","")

        if (userName != "")
            binding.editTextKullaniciAdi.setText(userName)

        when (userGenderID) {
            R.id.radioGenderKadin.toString() ->
                binding.radioGroup.check(R.id.radioGenderKadin)
            R.id.radioGenderErkek.toString() ->
                binding.radioGroup.check(R.id.radioGenderErkek)
            R.id.radioGenderBelirtilmedi.toString() ->
                binding.radioGroup.check(R.id.radioGenderBelirtilmedi)
        }
        binding.buttonBacktoHome.setOnClickListener{
            findNavController().navigate(R.id.action_userInfoScreen_to_homeFragment)
        }

        binding.buttonSaveUserInfo.setOnClickListener{
            if (inputCheck(binding.editTextKullaniciAdi.text.toString(),binding.radioGroup.checkedRadioButtonId.toString())){
                saveUserData()
                Toast.makeText(this.context, "Kayıt Başarılı", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_userInfoScreen_to_homeFragment)
            }
            else
                Toast.makeText(this.context, "Lütfen tüm alanları doldurunuz", Toast.LENGTH_SHORT).show()
        }
        return view
    }

    private fun saveUserData() {
        val sharedPref = requireActivity().getSharedPreferences("userInfo",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("userName",binding.editTextKullaniciAdi.text.toString())
        editor.putString("userGender",binding.radioGroup.checkedRadioButtonId.toString())
        editor.apply()
    }
    private fun inputCheck(
        userName: String,
        userGender: String,

        ): Boolean {
        return !(userName == "" || userGender == "-1")
    }
}