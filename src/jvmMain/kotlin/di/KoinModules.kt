package di

import data.remote.ClientApi
import data.remote.KtorClient
import data.repository.GamesRepositoryImpl
import domain.repository.GamesRepository
import org.koin.dsl.module
import presentation.games.GamesViewModel


val repositoryModule = module {
    single { GamesRepositoryImpl(get()) as GamesRepository }
}

val viewModelModule = module {
    single { GamesViewModel(get()) }
}

val networkModule = module {
    single { ClientApi(KtorClient.createClient()) }
}