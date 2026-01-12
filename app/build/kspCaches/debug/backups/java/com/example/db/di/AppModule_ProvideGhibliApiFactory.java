package com.example.db.di;

import com.example.db.data.api.GhibliApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideGhibliApiFactory implements Factory<GhibliApi> {
  @Override
  public GhibliApi get() {
    return provideGhibliApi();
  }

  public static AppModule_ProvideGhibliApiFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GhibliApi provideGhibliApi() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideGhibliApi());
  }

  private static final class InstanceHolder {
    static final AppModule_ProvideGhibliApiFactory INSTANCE = new AppModule_ProvideGhibliApiFactory();
  }
}
