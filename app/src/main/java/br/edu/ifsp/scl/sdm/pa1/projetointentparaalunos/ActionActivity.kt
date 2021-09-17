package br.edu.ifsp.scl.sdm.pa1.projetointentparaalunos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.sdm.pa1.projetointentparaalunos.databinding.ActivityActionBinding

class ActionActivity : AppCompatActivity() {
    private val activityActionBinding: ActivityActionBinding by lazy{
        ActivityActionBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityActionBinding.root)

        with (activityActionBinding.mainTb.appTb) {
            title = this@ActionActivity.javaClass.simpleName
            subtitle = intent.action
            setSupportActionBar(this)
        }
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let { activityActionBinding.parameterTv.text = it}
    }
}