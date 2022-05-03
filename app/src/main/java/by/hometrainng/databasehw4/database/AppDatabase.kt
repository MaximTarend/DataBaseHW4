package by.hometrainng.databasehw4.database

import androidx.room.Database
import androidx.room.RoomDatabase
import by.hometrainng.databasehw4.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
}