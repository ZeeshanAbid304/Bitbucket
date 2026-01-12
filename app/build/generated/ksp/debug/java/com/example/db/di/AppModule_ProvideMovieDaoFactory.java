package com.example.db.di;

import com.example.db.data.db.AppDatabase;
import com.example.db.data.db.MovieDao;
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
public final class AppModule_ProvideMovieDaoFactory implements Factory<MovieDao> {
  private final Provider<AppDatabase> databaseProvider;

  private AppModule_ProvideMovieDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public MovieDao get() {
    return provideMovieDao(databaseProvider.get());
  }

  public static AppModule_ProvideMovieDaoFactory create(Provider<AppDatabase> databaseProvider) {
    return new AppModule_ProvideMovieDaoFactory(databaseProvider);
  }

  public static MovieDao provideMovieDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideMovieDao(database));
  }
}
