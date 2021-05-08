package com.ertbaran.harcamatakip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ertbaran.harcamatakip.model.Harcama
import com.ertbaran.harcamatakip.databinding.RecyclerRowBinding
import com.ertbaran.harcamatakip.fragments.list.HomeFragmentDirections

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.HarcamalarViewHolder>() {

    private var harcamaListesi = emptyList<Harcama>()

    class HarcamalarViewHolder(val binding: RecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HarcamalarViewHolder {
        return HarcamalarViewHolder(
            RecyclerRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int {
        return harcamaListesi.size
    }

    override fun onBindViewHolder(holder: HarcamalarViewHolder, position: Int) {
        val currentItem = harcamaListesi[position]
        holder.binding.recyclerHarcamaAdiText.text = currentItem.harcamaAdi
        holder.binding.recyclerHarcamaFiyatText.text = (currentItem.harcamaFiyat.toString() + " " + currentItem.harcamaKuru)
        holder.binding.rowLayout.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToEditScreen(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        var image = R.drawable.ic_walleticon
        when (currentItem.harcamaTuru) {
            "Fatura" ->
                image = R.drawable.ic_bill
            "Kira" ->
                image = R.drawable.ic_rent
            "Gıda" ->
                image = R.drawable.ic_food
            "Diğer" ->
                image = R.drawable.ic_other
        }
        holder.binding.imageViewHarcama.setImageResource(image)
    }

    fun setData(harcama: List<Harcama>) {
        this.harcamaListesi = harcama
        notifyDataSetChanged()
    }
    fun getAllFiyat(harcama: List<Harcama>) {
        this.harcamaListesi = harcama
    }
}