package com.kotlin.primerparcial_albert.di

import android.content.Context
import androidx.room.Room
import com.kotlin.primerparcial_albert.data.local.DivisionDb
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
    fun provideDivisionDatabase(@ApplicationContext appContext: Context): DivisionDb =
        Room.databaseBuilder(
            appContext,
            DivisionDb::class.java,
            "Division.db")
            .fallbackToDestructiveMigration()
            .build()
    @Provides
    fun providesDivisionDao(db: DivisionDb) = db.divisionDao()
}