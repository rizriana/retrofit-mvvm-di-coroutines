package id.rrlab.withkoin.di.module

import id.rrlab.withkoin.data.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single { MainRepository(get()) }
}