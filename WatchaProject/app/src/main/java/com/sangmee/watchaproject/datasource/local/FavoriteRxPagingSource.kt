package com.sangmee.watchaproject.datasource.local

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.sangmee.watchaproject.model.Track
import com.sangmee.watchaproject.service.FavoriteDao
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FavoriteRxPagingSource @Inject constructor(private val favoriteService: FavoriteDao) :
    RxPagingSource<Int, Track>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Track>> {
        val position = params.key ?: 0
        return favoriteService.getAllFavorite(params.loadSize, position * params.loadSize)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it, position) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: List<Track>, position: Int): LoadResult<Int, Track> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == 0) null else position - 1,
            nextKey = if (position == data.size) null else position + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Track>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
