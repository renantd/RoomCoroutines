package com.example.room.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.room.model.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirUsuario(usuario: Usuario)

    @Query("SELECT * FROM Usuario")
    fun selecionarTodosUsuarios(): List<Usuario>

    @Query("DELETE FROM Usuario")
    fun deletarTodosRegistos()

}