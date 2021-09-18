package br.edu.ifsp.scl.sdm.pa1.projetointentparaalunos

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.sdm.pa1.projetointentparaalunos.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var requisicaoPermissoesActivityResultLauncher: ActivityResultLauncher<String>

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

        requisicaoPermissoesActivityResultLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { concedida ->
            if( !concedida ) {
                requisitarPermissaoLigacao()

            }
            else {
                discarTelefone()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.viewMi -> {
                val url = activityMainBinding.parameterEt.text.toString().let {
                    if (!it.contains("http[s]?".toRegex())) "http://${it}" else it
                }
                val siteIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(siteIntent)
                true
            }
            R.id.callMi -> {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        //requisitar permissao
                    } else{
                        //executar a discagem
                        discarTelefone()
                    }
                } else {
                    //executar discagem
                    discarTelefone()
                }
                true
            }
            R.id.dialMi -> {
                val ligacaoIntent = Intent(Intent.ACTION_DIAL)
                ligacaoIntent.data = Uri.parse("${activityMainBinding.parameterEt.text}")
                startActivity(ligacaoIntent)
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

    private fun discarTelefone() {
        val discarIntent = Intent()
        discarIntent.action = Intent.ACTION_CALL
        discarIntent.data = Uri.parse("tel: ${activityMainBinding.parameterEt.text}")
        startActivity(discarIntent)
    }

    private fun requisitarPermissaoLigacao() = requisicaoPermissoesActivityResultLauncher.launch((Manifest.permission.CALL_PHONE))
}