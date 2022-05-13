package com.example.room.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.R
import com.example.room.adapter.UsuarioRecyclerViewAdapter
import com.example.room.data.db.DataBase
import com.example.room.databinding.ActivityMainBinding
import com.example.room.model.Usuario
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)

        esconderTextMensagem()
        binding.rvResultado.visibility = View.GONE
        listarDados()

        binding.btnSalvar.setOnClickListener {
            salvar()
        }
        binding.btnListar.setOnClickListener {
          listar()
        }
        binding.btnDeletar.setOnClickListener {
            deletar()
        }

    }

    fun salvar(){

        var db = DataBase.iniciarDataBase(this)
        CoroutineScope(Dispatchers.IO).launch {
            if(!binding.editNome.text.toString().trim().isNullOrBlank() &&
                !binding.editEmail.text.toString().trim().isNullOrBlank()){
                db.usuarioDao().inserirUsuario(Usuario(binding.editNome.text.toString() , binding.editEmail.text.toString()))
                limparCampos()
            }
        }
    }
    fun deletar(){
        var db = DataBase.iniciarDataBase(this)

        CoroutineScope(Dispatchers.IO).launch {
            db.usuarioDao().deletarTodosRegistos()
            var x = db.usuarioDao().selecionarTodosUsuarios()

            if(x.isEmpty()){
                runOnUiThread {
                    Toast.makeText(binding.root.context , "Registro excluido com sucesso !" , Toast.LENGTH_LONG).show()
                }

            }
        }
    }
    fun listar(){

        mostrarTextMensagem()
        var db = DataBase.iniciarDataBase(this)
        var word = ""

        CoroutineScope(Dispatchers.IO).launch {
            var x = db.usuarioDao().selecionarTodosUsuarios()
            x.forEach {
                word = word + "ID: ${it.id} / nome: ${it.nome} / email: ${it.email}"+ "\n"
            }
            binding.tvResultado.text = "$word"
        }
    }
    fun listarDados(){

        CoroutineScope(Dispatchers.IO).launch {
            var db = DataBase.iniciarDataBase(binding.root.context)
            var x = db.usuarioDao().selecionarTodosUsuarios()

            if(x.isNotEmpty()){
                x.forEach {
                    Log.e("Main" , "${it.nome}")
                }
            }else{
                Log.e("Main" , "Nenhum Registro ")
            }
        }

    }

    private fun initAdapter(listUsuario: List<Usuario>){
        val layoutManager = LinearLayoutManager(this)
        binding.rvResultado.layoutManager = layoutManager
        binding.rvResultado.adapter = UsuarioRecyclerViewAdapter(listUsuario)
    }

    fun limparCampos() {
        binding.editNome.setText("")
        binding.editEmail.setText("")
    }
    fun esconder(){
        binding.cardResultado.visibility = View.GONE
    }
    fun mostrar(){
        binding.cardResultado.visibility = View.VISIBLE
    }
    fun esconderTextMensagem(){
        binding.tvResultado.visibility = View.GONE
    }
    fun mostrarTextMensagem(){
        binding.tvResultado.visibility = View.VISIBLE
    }

}

