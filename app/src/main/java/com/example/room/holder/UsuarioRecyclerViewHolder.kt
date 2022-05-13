package com.example.room.holder

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.room.data.db.DataBase
import com.example.room.databinding.ItemListBinding
import com.example.room.model.Usuario
import kotlinx.coroutines.*

class UsuarioRecyclerViewHolder(
    val  binding: ItemListBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(usuario: Usuario) {

        //Log.e("HolderRecycler" , "\nHolder: ID${usuario.id} - Nome${usuario.nome} - Email${usuario.email}")
        binding.tvIdUsaurio.text = "${usuario.id}"
        binding.tvNomeUsaurio.text = "${usuario.nome}"
        binding.tvEmailUsaurio.text = "${usuario.email}"

    }
}