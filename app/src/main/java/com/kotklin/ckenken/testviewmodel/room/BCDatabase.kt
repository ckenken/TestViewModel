package com.kotklin.ckenken.testviewmodel.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DataEntity::class],
    version = 1
)
abstract class BCDatabase: RoomDatabase() {
    //取得 dao 的方法
    abstract val dataDao: DataDao

    //官方推薦的 Singleton 寫法，因為實體的產生很耗資源，而且也不需要多個資料庫實體
    companion object {
        private const val DATABASE_NAME = "bc_database"

        // For Singleton instantiation
        @Volatile
        private lateinit var INSTANCE: BCDatabase

        fun getInstance(context: Context): BCDatabase {
            if (!::INSTANCE.isInitialized) {
                synchronized(this) {
                    if (!::INSTANCE.isInitialized) {
                        buildDatabase(context).also { INSTANCE = it }
                    }
                }
            }

            return INSTANCE
        }

        private fun buildDatabase(context: Context): BCDatabase {
            return Room.databaseBuilder(context, BCDatabase::class.java, DATABASE_NAME)
                .build()
        }

//        @Volatile
//        private var INSTANCE: BCDatabase? = null
//
//        fun getInstance(context: Context): BCDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance =
//                    Room.databaseBuilder(
//                        context.applicationContext,
//                        BCDatabase::class.java,
//                        "bc_database"
//                    ).fallbackToDestructiveMigration()
//                        .allowMainThreadQueries()
//                        .build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
    }

}