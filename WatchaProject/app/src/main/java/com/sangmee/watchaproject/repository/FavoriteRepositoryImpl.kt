package com.sangmee.watchaproject.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.sangmee.watchaproject.datasource.local.FavoriteLocalDataSource
import com.sangmee.watchaproject.datasource.local.FavoriteRxPagingSource
import com.sangmee.watchaproject.model.Track
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject
import javax.inject.Singleton

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteLocalDataSource: FavoriteLocalDataSource,
    private val favoriteRxPagingSource: FavoriteRxPagingSource
) :
    FavoriteRepository {

    override fun getAllFavoriteTrack(): Flowable<PagingData<Track>> =
        Pager(
            PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { favoriteRxPagingSource }
        ).flowable

    override fun saveFavoriteTrack(track: Track): Completable {
        return favoriteLocalDataSource.saveFavoriteTrack(track)
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class FavoriteRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFavoriteRepository(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository
}
