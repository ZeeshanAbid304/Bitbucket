package com.example.db.presentation.movie_list;

import com.example.db.domain.repository.MovieRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class MovieListViewModel_Factory implements Factory<MovieListViewModel> {
  private final Provider<MovieRepository> repositoryProvider;

  private MovieListViewModel_Factory(Provider<MovieRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public MovieListViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static MovieListViewModel_Factory create(Provider<MovieRepository> repositoryProvider) {
    return new MovieListViewModel_Factory(repositoryProvider);
  }

  public static MovieListViewModel newInstance(MovieRepository repository) {
    return new MovieListViewModel(repository);
  }
}
