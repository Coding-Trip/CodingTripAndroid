package coding_trip.io.android

import android.app.Application
import coding_trip.io.android.di.applicationModule
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.lazy

class BaseApplication : Application(), KodeinAware {

    override val kodein: Kodein by Kodein.lazy {
        import(applicationModule(this@BaseApplication))
    }
}