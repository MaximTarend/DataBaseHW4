package by.hometrainng.databasehw4

import android.app.Application
import android.content.Context
import androidx.room.Room
import by.hometrainng.databasehw4.database.AppDatabase

class DataBaseHW4: Application() {

    private var _appDatabase: AppDatabase? = null
    val appDatabase get() = requireNotNull(_appDatabase)

    override fun onCreate() {
        super.onCreate()
        _appDatabase = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "app_database.db"
        ).allowMainThreadQueries()
            .build()
    }
}

val Context.appDatabase: AppDatabase
    get() = when (this) {
        is DataBaseHW4 -> appDatabase
        else -> applicationContext.appDatabase
    }