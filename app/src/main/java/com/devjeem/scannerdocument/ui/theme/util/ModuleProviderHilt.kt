package com.devjeem.scannerdocument.ui.theme.util

import android.content.Context
import com.devjeem.scannerdocument.ui.theme.camera.UtilCamera
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ModuleProviderHilt {

    @Provides
    @Singleton
    fun providePhoto(@ApplicationContext context: Context) = UtilCamera(context)

}