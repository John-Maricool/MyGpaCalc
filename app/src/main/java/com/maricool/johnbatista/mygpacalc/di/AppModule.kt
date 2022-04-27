package com.maricool.johnbatista.mygpacalc.di

import android.content.Context
import androidx.room.Room
import com.maricool.johnbatista.mygpacalc.local_db.CourseDao
import com.maricool.johnbatista.mygpacalc.local_db.CourseDatabase
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
    fun provideCourseDb(@ApplicationContext context: Context): CourseDatabase =
        Room.databaseBuilder(
            context,
            CourseDatabase::class.java,
            "course_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideCourseDao(db: CourseDatabase): CourseDao {
        return db.courseDao()
    }
}