package com.example.todo.database.room

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameTable
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todo.database.dao.TodoDao

import com.example.todo.database.model.Todo


@Database([Todo::class], version = 1)
 abstract class TodoRoomDataBase: RoomDatabase() {
     abstract fun todoDao():TodoDao
     companion object{
         private var databaseRoom:TodoRoomDataBase?=null
         fun getInstance(context: Context):TodoRoomDataBase{
             if(databaseRoom==null){
                 Room.databaseBuilder(
                     context.applicationContext,
                     RoomDatabase::class.java, "database-name"
                 ).build()
             }
             return databaseRoom!!
         }
     }
}



//@Database(
  //  version = 2,
  //  entities = [User::class],
    //autoMigrations = [
      //  AutoMigration (
        //    from = 1,
          //  to = 2,
            //spec = UserDatabase.UserAutoMigration::class
  //      )
    //]
//)
//abstract class UserDatabase : RoomDatabase() {
  //  abstract fun userDao(): UserDao

    //class UserAutoMigration: AutoMigrationSpec {

      //  override fun onPostMigrate(db: SupportSQLiteDatabase) {
        //    super.onPostMigrate(db)
        //}
    //}
//}

