package com.kotklin.ckenken.testviewmodel.room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource(private val dataDao: DataDao, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {
    suspend fun getCommentContents(): LiveData<List<ContentData>> = withContext(ioDispatcher) {
        dataDao.getAllComments().map {
            it.forEach {
                Log.d("ckenken", "LocalDataSource.getCommentContents it.content = ${it.content}")
            }
            it.asDomainModel()
        }
    }

    suspend fun saveComment(id: Int, comment: String, title: String) = withContext(ioDispatcher) {
        try {
            dataDao.apply {
//                clearAll()
                insertComment(DataEntity(id, comment, title))
            }
        } catch (e: Exception) {
            Log.e("ckenken", "", e)
        }
    }

    suspend fun clearAll() = withContext(ioDispatcher) {
        try {
            dataDao.clearAll()
        } catch (e: Exception) {
            Log.e("ckenken", "", e)
        }
    }
}