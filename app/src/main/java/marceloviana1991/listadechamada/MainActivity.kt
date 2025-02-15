package marceloviana1991.listadechamada

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
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
            val intent = Intent(
                this,
                ExcluirActivity::class.java
            ).apply {
                putExtra("NOME", it)
            }
            startActivity(intent)
        }

        val floatingActionButton = biding.floatingActionButton
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(NomesDao.buscarTodos())

    }
}