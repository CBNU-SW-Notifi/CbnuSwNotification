package di

fun appModule() =
    listOf(provideHttpClientModule, provideRepositoryModule, provideviewModelModule, platformModule)