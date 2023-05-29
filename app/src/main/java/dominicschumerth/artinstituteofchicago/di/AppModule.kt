package dominicschumerth.artinstituteofchicago.di

import android.content.Context
import androidx.room.Room
import com.dschumerth.api.ApiService
import com.dschumerth.api.constants.Constants
import com.dschumerth.database.AppRoomDatabase
import com.dschumerth.database.daos.ArtworkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppRoomDatabase =
        Room.databaseBuilder(context, AppRoomDatabase::class.java, Constants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideArtworkDao(appRoomDatabase: AppRoomDatabase): ArtworkDao =
        appRoomDatabase.appDao()
}
