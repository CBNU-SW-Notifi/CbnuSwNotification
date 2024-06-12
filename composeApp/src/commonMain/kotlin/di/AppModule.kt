package di

fun appModule() =
    listOf(
        provideHttpClientModule,
        provideJobHuntRepositoryModule,
        platformModule,
        provideViewModelModule
    )