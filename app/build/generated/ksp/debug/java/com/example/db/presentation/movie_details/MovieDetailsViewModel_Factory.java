package com.example.db.presentation.movie_details;

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
public final class MovieDetailsViewModel_Factory implements Factory<MovieDetailsViewModel> {
  private final Provider<MovieRepository> repositoryProvider;

  private MovieDetailsViewModel_Factory(Provider<MovieRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public MovieDetailsViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static MovieDetailsViewModel_Factory create(Provider<MovieRepository> repositoryProvider) {
    return new MovieDetailsViewModel_Factory(repositoryProvider);
  }

  public static MovieDetailsViewModel newInstance(MovieRepository repository) {
    return new MovieDetailsViewModel(repository);
  }
}
