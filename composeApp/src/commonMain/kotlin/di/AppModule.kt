package di

fun appModule() =
    listOf(
        provideHttpClientModule,
        provideRepositoryModule,
        platformModule,
        provideviewModelModule
    )