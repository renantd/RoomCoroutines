package com.example.room.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.data.dao.UsuarioDao
import com.example.room.model.Usuario

@Database(entities = [Usuario::class] , version = 1 , exportSchema = false)
abstract class DataBase: RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    companion object{

        @Volatile private var INSTANCE: DataBase? = null
        private val LOCK = Any()

        fun iniciarDataBase(context: Context): DataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "ROOM.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return  instance
            }
        }

        /* Jeito 2
        operator fun invoke(context: Context)= INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it}
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            DataBase::class.java, "Room1.db")
            .build()*/

    }

}