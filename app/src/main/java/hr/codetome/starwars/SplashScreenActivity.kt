package hr.codetome.starwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.codetome.starwars.databinding.ActivitySplashScreenBinding
import hr.codetome.starwars.framework.*

private const val DELAY = 3000L
const val DATA_IMPORTED = "hr.codetome.starwars.data_imported"

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startAnimations()
        redirect()
    }

    private fun startAnimations() {
        binding.tvSplash.applyAnimation(R.anim.blink)
        binding.ivSplash.applyAnimation(R.anim.scale)
    }

    private fun redirect() {

        callDelayed(DELAY) { startActivity<HostActivity>() }

//        if (getBooleanPreference(DATA_IMPORTED)) {
//            callDelayed(DELAY) { startActivity<HostActivity>() }
//
//        } else {
//            if (isOnline()) {
//                NasaService.enqueue(this)
//            } else {
//                binding.tvSplash.text = getString(R.string.no_internet)
//                callDelayed(DELAY) { finish() }
//            }
//        }
    }

}