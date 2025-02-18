package marceloviana1991.listadechamada

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import marceloviana1991.listadechamada.databinding.ActivityExcluirBinding

class ExcluirActivity : AppCompatActivity() {

    private val biding by lazy {
        ActivityExcluirBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(biding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nome = intent.getStringExtra("NOME")

        biding.textView.text = nome

        val button = biding.button
        button.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Botão excluir")
                .setMessage("Deseja realmente excluir \"$nome\"?")
                .setPositiveButton("CONFIRMAR" ) { _, _ ->
                    NomesDao.excluir(nome)
                    Toast.makeText(this, "Cadastro excluido com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                }
                .setNegativeButton("CANCELAR") { _, _ ->

                }
                .show()
        }
    }
}