package com.example.todoapp.di

import com.example.todoapp.data.repository.ToDoAppRepository
import com.example.todoapp.data.repository.ToDoAppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingAppModule {

    @Binds
    abstract fun bindToDoAppRepository(toDoAppRepositoryImpl: ToDoAppRepositoryImpl):ToDoAppRepository
}