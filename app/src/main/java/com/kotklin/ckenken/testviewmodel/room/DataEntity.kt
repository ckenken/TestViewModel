package com.kotklin.ckenken.testviewmodel.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment_table")
data class DataEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") var id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String
)

/**
 * Convert Database results to domain objects
 */
fun List<DataEntity>.asDomainModel(): List<ContentData> {
    return map {
        ContentData(it.content, it.title)
    }
}