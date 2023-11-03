package com.example.todoapp.data.local

import androidx.room.TypeConverter

object IntListConverter {

    @TypeConverter
    @JvmStatic
    fun IntListToString(list: List<Int>): String? =


        if(list.isEmpty())  ""
        else list.joinToString(separator = "|")


    @TypeConverter
    @JvmStatic
    fun stringToIntList(string: String?): List<Int> {

      return  if (string.isNullOrBlank()) listOf()
        else {
            val listofInt = mutableListOf<Int>()
            for (stringId in string.split("|")){
                listofInt.add(stringId.toInt())
            }
          listofInt
        }
    }


}

