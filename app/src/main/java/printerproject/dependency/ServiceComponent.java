package printerproject.dependency;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import dagger.Component;
import printerproject.activity.modelActivities.DeleteModelActivity;
import printerproject.activity.modelActivities.GetModelActivity;
import printerproject.activity.modelActivities.UpdateModelActivity;
import printerproject.requests.modelRequests.DeleteModelRequest;
import printerproject.requests.modelRequests.UpdateModelRequest;

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
}