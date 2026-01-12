package com.example.db.data.repository;

import com.example.db.data.api.GhibliApi;
import com.example.db.data.db.MovieDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class MovieRepositoryImpl_Factory implements Factory<MovieRepositoryImpl> {
  private final Provider<GhibliApi> apiProvider;

  private final Provider<MovieDao> daoProvider;

  private MovieRepositoryImpl_Factory(Provider<GhibliApi> apiProvider,
      Provider<MovieDao> daoProvider) {
    this.apiProvider = apiProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public MovieRepositoryImpl get() {
    return newInstance(apiProvider.get(), daoProvider.get());
  }

  public static MovieRepositoryImpl_Factory create(Provider<GhibliApi> apiProvider,
      Provider<MovieDao> daoProvider) {
    return new MovieRepositoryImpl_Factory(apiProvider, daoProvider);
  }

  public static MovieRepositoryImpl newInstance(GhibliApi api, MovieDao dao) {
    return new MovieRepositoryImpl(api, dao);
  }
}
