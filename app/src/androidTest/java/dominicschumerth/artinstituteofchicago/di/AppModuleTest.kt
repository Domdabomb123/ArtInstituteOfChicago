package dominicschumerth.artinstituteofchicago.di

import android.content.Context
import androidx.room.Room
import com.dschumerth.database.AppRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModuleTest {
    @Provides
    @Named("art_institute_database")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, AppRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}