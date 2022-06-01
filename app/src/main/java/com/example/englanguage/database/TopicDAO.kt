package com.example.englanguage.database;
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.englanguage.model.topic.Success
import com.example.englanguage.model.vocabulary.SuccessVocabulary

@Dao
interface TopicDAO {

    @Insert
    fun insertTopic(success: Success)

    @Query("SELECT * FROM topics")
    fun getListTopic() : List<Success>
}

@Dao
interface VocabularyDAO {

    @Insert
    fun insertVocabulary(successVocabulary: SuccessVocabulary)

    @Query("SELECT * FROM vocabulary")
    fun getListVocabulary() : List<SuccessVocabulary>
}