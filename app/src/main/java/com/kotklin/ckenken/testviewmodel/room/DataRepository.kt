package com.kotklin.ckenken.testviewmodel.room

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepository(private val localDataSource: LocalDataSource, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {
    suspend fun getCommentContents(): LiveData<List<ContentData>> =
        withContext(ioDispatcher) {
            localDataSource.getCommentContents()
        }

    suspend fun insertContent(content: String, title: String) {
        withContext(ioDispatcher) {
            Log.d("ckenken", "insertContent(): getCommentContents().value = ${getCommentContents().value}")
            if (getCommentContents().value != null) {
                Log.d("ckenken", "insertContent(): getCommentContents().value.size = ${getCommentContents().value!!.size}")
                localDataSource.saveComment(getCommentContents().value!!.size, content, title)
            } else {
                Log.d("ckenken", "insertContent(): 0 insert")
                localDataSource.saveComment(0, content, title)
            }
        }
    }

    suspend fun clearAll() {
        withContext(ioDispatcher) {
            localDataSource.clearAll()
        }
    }


    suspend fun refreshHowTos() =
        withContext(ioDispatcher) {
//            val remoteData = howToRemoteDataSource.getHowTos()
//
//            if (remoteData.succeeded) {
//                howToLocalDataSource.saveHowTos(remoteData.data!!)
//            }
        }
}