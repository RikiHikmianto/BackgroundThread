package co.id.rikihikmianto.mybackgroundthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import co.id.rikihikmianto.mybackgroundthread.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Default){
                //simulate process in background thread
                for (i in 0..10){
                    delay(500)
                    val percentage = i*10
                    withContext(Dispatchers.Main){
                        //update ui in main thread
                        if (percentage == 100){
                            binding.tvStatus.setText(R.string.task_completed)
                        }else
                        {
                            binding.tvStatus.text = String.format(getString(R.string.compressing), percentage)
                        }
                    }
                }
            }
        }
    }
}