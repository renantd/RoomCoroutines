package com.example.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ItemListBinding
import com.example.room.holder.UsuarioRecyclerViewHolder
import com.example.room.model.Usuario

class UsuarioRecyclerViewAdapter(private var usuario: List<Usuario>):
    RecyclerView.Adapter<UsuarioRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioRecyclerViewHolder {

      val inflater = LayoutInflater.from(parent.context)
      val binding = ItemListBinding.inflate(inflater , parent , false)
      return UsuarioRecyclerViewHolder(binding)

    }
    override fun getItemCount() = usuario.size
    override fun onBindViewHolder(holder: UsuarioRecyclerViewHolder, position: Int) {
        val usuario = usuario[position]
        holder.bind(usuario)
    }

}