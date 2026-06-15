package com.github.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.domain.entity.ImprovementEntity

@Database(
    entities = [
        ImprovementEntity::class
    ], version = 1
)
//@TypeConverter(RoomTypeConverter::class)
abstract class MAINTLOG : RoomDatabase() {
    abstract fun improvementDao(): ImprovementDao
}