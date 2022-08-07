package com.example.newsappmvvm.db

import androidx.room.TypeConverter
import com.example.newsappmvvm.models.Source

class Converter {

    @TypeConverter
    fun fromSource(source: Source) : String{
        return source.name
    }

    @TypeConverter
    fun toSource(string: String): Source{
        return Source(string, string)
    }

}