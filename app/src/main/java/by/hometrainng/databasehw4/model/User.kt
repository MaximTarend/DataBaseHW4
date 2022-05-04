package by.hometrainng.databasehw4.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "firstName")
    val firstName: String = "Ivan",
    @ColumnInfo(name = "lastName")
    val lastName: String = "Ivanov",
)