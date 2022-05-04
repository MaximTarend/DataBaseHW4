package by.hometrainng.databasehw4.model

object UserList {

    private var userList = mutableListOf<User>()

    fun setList(list: List<User>) {
        userList = list.toList() as MutableList<User>
    }

    fun getUserList(): List<User> {
        return userList.toList()
    }
}