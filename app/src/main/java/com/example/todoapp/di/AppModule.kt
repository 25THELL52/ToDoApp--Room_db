package com.example.todoapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.todoapp.data.local.ToDoAppDao
import com.example.todoapp.data.local.ToDoAppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): ToDoAppDataBase {
        val db = Room.databaseBuilder(
            context,
            ToDoAppDataBase::class.java, "database-name"
        ).build()
        return db
    }

    @Provides
    fun providesDao(toDoAppDataBase: ToDoAppDataBase):ToDoAppDao {
       return toDoAppDataBase.getDao()
    }

}