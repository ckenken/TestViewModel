package com.kotklin.ckenken.testviewmodel.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DataDao {
    @Query("select * from comment_table limit 10")
    fun getAllComments(): LiveData<List<DataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComment(comment: DataEntity)

    /**
     * Delete all data in the database.
     */
    @Query("DELETE FROM comment_table")
    suspend fun clearAll()
}