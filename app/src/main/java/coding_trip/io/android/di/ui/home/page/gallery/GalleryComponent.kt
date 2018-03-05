package coding_trip.io.android.di.ui.home.page.gallery

import coding_trip.io.android.BaseApplication
import coding_trip.io.android.di.AppComponent
import dagger.Component

@GalleryScope
@Component(dependencies = [AppComponent::class], modules = [GalleryModule::class])
interface GalleryComponent {

    object Initializer {
        fun init(): GalleryComponent =
                DaggerGalleryComponent.builder()
                        .appComponent(BaseApplication.appComponent)
                        .galleryModule(GalleryModule())
                        .build()
    }

}
