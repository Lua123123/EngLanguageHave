package com.example.englanguage.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.englanguage.model.topic.Success;
import com.example.englanguage.model.vocabulary.SuccessVocabulary;


@Database(entities = {Success.class, SuccessVocabulary.class}, version = 1)
public abstract class VocabularyDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "xxx.db";
    private static VocabularyDatabase instance;

    public static synchronized VocabularyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), VocabularyDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract TopicDAO topicDAO();
    public abstract VocabularyDAO vocabularyDAO();
}
