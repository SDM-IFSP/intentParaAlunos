package br.edu.ifsp.scl.sdm.pa1.projetointentparaalunos

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.sdm.pa1.projetointentparaalunos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        with(activityMainBinding.mainTb.appTb) {
            title = "Tratando Intents"
            subtitle = "Principais tipos"
        }

        // Usando Toolbar como ActionBar da Activity
        setSupportActionBar(activityMainBinding.mainTb.appTb)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.viewMi -> {
                true
            }
            R.id.callMi -> {
                true
            }
            R.id.dialMi -> {
                true
            }
            R.id.pickMi -> {
                true
            }
            R.id.chooserMi -> {
                true
            }
            R.id.exitMi -> {
                finish()
                true
            }
            R.id.actionMi -> {
                // Abrindo outra activity usando uma Intent Action
                val actionIntent = Intent("OPEN_ACTION_ACTIVITY").putExtra(
                    Intent.EXTRA_TEXT,
                    activityMainBinding.parameterEt.text.toString()
                )
                startActivity(actionIntent)
                true
            }
            else -> {
                false
            }
        }
    }
}