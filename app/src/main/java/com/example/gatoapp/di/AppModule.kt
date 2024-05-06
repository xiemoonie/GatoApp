package com.example.gatoapp.di

import android.content.Context
import androidx.room.Room
import com.example.gatoapp.Constants.BASE_URL
import com.example.gatoapp.Constants.DATABASE_NAME
import com.example.gatoapp.data.GatoAPI
import com.example.gatoapp.data.GatoRepository
import com.example.gatoapp.database.DataBase
import com.example.gatoapp.database.GatoDao
import com.example.gatoapp.ui.GatoViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single<GatoRepository> { GatoRepository(gatoAPI = get(), gatoDao = get()) }
    single<GatoDao> {
        val database: DataBase = get()
        database.gatoDao()
    }
    single<GatoAPI> {
        val retrofit: Retrofit = get()
        retrofit.create(GatoAPI::class.java)
    }

    single<DataBase> {
        val context: Context = get()
        Room.databaseBuilder(context.applicationContext, DataBase::class.java, DATABASE_NAME)
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }
    viewModel { GatoViewModel(repository = get()) }

}
