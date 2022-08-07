package com.example.newsappmvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsappmvvm.models.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converter::class)
abstract class ArticlesDataBase : RoomDatabase() {

    companion object{

        @Volatile
        private var instance: ArticlesDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDataBase(context)
        }

        private fun buildDataBase(context: Context): ArticlesDataBase{
            return Room.databaseBuilder(
                context,
                ArticlesDataBase::class.java,
                "articles_db"
            ).build()
        }

    }

    abstract fun getNewsDao():NewsDao

}



/*
companion object{
        val NAME : String = "DataBase"
        @Volatile private var instance: NotesDataBase? = null
        fun getInstance(context: Context): NotesDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

//        fun getInstance(appContext: Application): NotesDataBase {
//            if (instance == null){
//                instance = Room.databaseBuilder(
//                    appContext,
//                    NotesDataBase::class.java,
//                    NAME
//
//                ).allowMainThreadQueries().build()
//
//                return instance as NotesDataBase
//            }
//            return instance as NotesDataBase
//        }

        private fun buildDatabase(context: Context): NotesDataBase{
            return  Room.databaseBuilder(context,  NotesDataBase::class.java, NAME).build()

        }
    }
 */