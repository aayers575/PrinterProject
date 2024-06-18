package printerproject.dependency;

import dagger.Component;
import printerproject.activity.filamentActivities.CreateFilamentActivity;
import printerproject.activity.filamentActivities.DeleteFilamentActivity;
import printerproject.activity.filamentActivities.GetAllFilamentsActivity;
import printerproject.activity.filamentActivities.GetFilamentActivity;
import printerproject.activity.filamentActivities.GetFilamentForColorActivity;
import printerproject.activity.filamentActivities.UpdateFilamentActivity;
import printerproject.activity.modelActivities.CreateModelActivity;
import printerproject.activity.modelActivities.DeleteModelActivity;
import printerproject.activity.modelActivities.GetModelActivity;
import printerproject.activity.modelActivities.GetModelsForKeywordActivity;
import printerproject.activity.modelActivities.UpdateModelActivity;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return GetModelActivity
     */
    GetModelActivity provideGetModelActivity();
    /**
     * Provides the relevant activity.
     * @return DeleteModelActivity
     */
    DeleteModelActivity provideDeleteModelActivity();
    /**
     * Provides the relevant activity.
     * @return UpdateModelActivity
     */
    UpdateModelActivity provideUpdateModelActivity();
    /**
     * Provides the relevant activity.
     * @return CreateModelActivity
     */
    CreateModelActivity provideCreateModelActivity();
    /**
     * Provides the relevant activity.
     * @return CreateFilamentActivity
     */
    CreateFilamentActivity provideCreateFilamentActivity();
    /**
     * Provides the relevant activity.
     * @return DeleteFilamentActivity
     */
    DeleteFilamentActivity provideDeleteFilamentActivity();
    /**
     * Provides the relevant activity.
     * @return GetFilamentActivity
     */
    GetFilamentActivity provideGetFilamentActivity();
    /**
     * Provides the relevant activity.
     * @return UpdateFilamentActivity
     */
    UpdateFilamentActivity provideUpdateFilamentActivity();
    /**
     * Provides the relevant activity.
     * @return GetModelsForKeywordActivity
     */
    GetModelsForKeywordActivity provideGetModelsForKeywordActivity();
    /**
     * Provides the relevant activity.
     * @return GetFilamentForColorActivity
     */
    GetFilamentForColorActivity provideGetFilamentsForColorActivity();
    /**
     * Provides the relevant activity.
     * @return GetAllFilamentsActivity
     */
    GetAllFilamentsActivity provideGetAllFilamentsActivity();

}
