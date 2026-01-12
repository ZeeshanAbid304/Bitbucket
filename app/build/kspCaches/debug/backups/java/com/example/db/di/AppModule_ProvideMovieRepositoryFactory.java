package com.example.db.di;

import com.example.db.data.api.GhibliApi;
import com.example.db.data.db.MovieDao;
import com.example.db.domain.repository.MovieRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class AppModule_ProvideMovieRepositoryFactory implements Factory<MovieRepository> {
  private final Provider<GhibliApi> apiProvider;

  private final Provider<MovieDao> daoProvider;

  private AppModule_ProvideMovieRepositoryFactory(Provider<GhibliApi> apiProvider,
      Provider<MovieDao> daoProvider) {
    this.apiProvider = apiProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public MovieRepository get() {
    return provideMovieRepository(apiProvider.get(), daoProvider.get());
  }

  public static AppModule_ProvideMovieRepositoryFactory create(Provider<GhibliApi> apiProvider,
      Provider<MovieDao> daoProvider) {
    return new AppModule_ProvideMovieRepositoryFactory(apiProvider, daoProvider);
  }

  public static MovieRepository provideMovieRepository(GhibliApi api, MovieDao dao) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideMovieRepository(api, dao));
  }
}
