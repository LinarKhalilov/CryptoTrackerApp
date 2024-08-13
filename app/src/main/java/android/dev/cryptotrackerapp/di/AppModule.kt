package android.dev.cryptotrackerapp.di

import android.dev.cryptotrackerapp.data.CryptoApi
import android.dev.cryptotrackerapp.data.CryptoRepository
import android.dev.cryptotrackerapp.domain.ICryptoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.coingecko.com/api/v3/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCoinGeckoApiService(): CryptoApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CryptoApi::class.java)

}

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Singleton
    @Binds
    fun bindCryptoRepository(
        cryptoRepository: CryptoRepository
    ): ICryptoRepository
}