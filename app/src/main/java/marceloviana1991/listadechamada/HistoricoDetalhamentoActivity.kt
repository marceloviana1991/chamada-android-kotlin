package marceloviana1991.listadechamada

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import marceloviana1991.listadechamada.databinding.ActivityHistoricoBinding
import marceloviana1991.listadechamada.databinding.ActivityHistoricoDetalhamentoBinding

class HistoricoDetalhamentoActivity : AppCompatActivity() {

    private val biding by lazy {
        ActivityHistoricoDetalhamentoBinding.inflate(layoutInflater)
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

        val position = intent.extras?.getInt("HISTORICO")
        val chamada = ChamadaDao.buscaTodas()
        val historicoDetalhamento = position?.let { chamada.get(it) }?.ausentes

        val adapter = historicoDetalhamento?.let {
            ArrayAdapter(
                this, android.R.layout.simple_list_item_1, it.toArray()
            )
        }

        biding.listView.adapter = adapter
    }
}