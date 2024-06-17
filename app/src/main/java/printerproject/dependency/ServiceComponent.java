package printerproject.dependency;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import dagger.Component;
import printerproject.activity.filamentActivities.*;
import printerproject.activity.modelActivities.*;
import printerproject.requests.filamentRequests.GetAllFilamentsRequest;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return AddSongToPlaylistActivity
     */
    GetModelActivity provideGetModelActivity();

    DeleteModelActivity provideDeleteModelActivity();

    UpdateModelActivity provideUpdateModelActivity();

    CreateModelActivity provideCreateModelActivity();

    CreateFilamentActivity provideCreateFilamentActivity();

    DeleteFilamentActivity provideDeleteFilamentActivity();

    GetFilamentActivity provideGetFilamentActivity();

    UpdateFilamentActivity provideUpdateFilamentActivity();

    GetModelsForKeywordActivity provideGetModelsForKeywordActivity();

    GetFilamentForColorActivity provideGetFilamentsForColorActivity();

    GetAllFilamentsActivity provideGetAllFilamentsActivity();

}