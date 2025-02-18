package com.example.proffer.di

import com.example.proffer.data.remote.ApiService
import com.example.proffer.data.repository.LoginRepoImpl
import com.example.proffer.data.repository.ProfileRepoImpl
import com.example.proffer.data.repository.RegisterRepoImpl
import com.example.proffer.data.repository.VerificationRepoImpl
import com.example.proffer.domain.repository.LoginRepository
import com.example.proffer.domain.repository.ProfileRepository
import com.example.proffer.domain.repository.RegisterRepository
import com.example.proffer.domain.repository.VerificationRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRegisterRepo(apiService: ApiService): RegisterRepository {
        return RegisterRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideVerificationRepo(apiService: ApiService): VerificationRepo {
        return VerificationRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideLoginRepo(apiService: ApiService): LoginRepository {
        return LoginRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideProfileRepo(apiService: ApiService): ProfileRepository {
        return ProfileRepoImpl(apiService)
    }

}