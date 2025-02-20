package marceloviana1991.listadechamada

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import marceloviana1991.listadechamada.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val biding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter = MainAdapter(this, NomesDao.buscarTodos())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(biding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = biding.recyclerview
        recyclerView.adapter = adapter

        adapter.quandoClicaNoItemListener = {
           AlertDialog.Builder(this)
                .setTitle("Botão excluir")
                .setMessage("Deseja realmente excluir \"$it\"?")
                .setPositiveButton("CONFIRMAR" ) { _, _ ->
                    NomesDao.excluir(it)
                    Toast.makeText(this, "Cadastro excluido com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                    adapter.atualiza(NomesDao.buscarTodos())
                }
                .setNegativeButton("CANCELAR") { _, _ ->

                }
                .show()
        }

        val floatingActionButton = biding.floatingActionButton
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        val button = biding.button
        button.setOnClickListener {
            val intent = Intent(
                this,
                ChamadaActivity::class.java
            ).apply {
                putStringArrayListExtra("CHAMADA", ArrayList(adapter.finaliza()))
            }
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(NomesDao.buscarTodos())

    }
}