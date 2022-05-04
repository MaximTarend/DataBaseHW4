package by.hometrainng.databasehw4.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import by.hometrainng.databasehw4.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUsers(): List<User>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: Long): User

    @Insert(onConflict = REPLACE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}