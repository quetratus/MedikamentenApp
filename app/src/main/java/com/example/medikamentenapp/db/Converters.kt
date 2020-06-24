package com.example.medikamentenapp.db

import androidx.room.TypeConverter
import java.sql.Time

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Time? {
            return value?.let { Time(it) }
        }

    @TypeConverter
    fun timeToTimestamp(date: Time?): Long? {
        return date?.time?.toLong()
        }

}