package marceloviana1991.listadechamada

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import marceloviana1991.listadechamada.databinding.ActivityHistoricoBinding
import java.time.format.DateTimeFormatter

class HistoricoActivity : AppCompatActivity() {

    private val biding by lazy {
        ActivityHistoricoBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(biding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        title = "Histórico de Chamadas"

        val historico = mutableListOf<String>()
        val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

        ChamadaDao.buscaTodas().forEach {
            historico.add(it.dateTime.format(formato) + " - " + it.codigoDoResponsavel)
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, historico)

        biding.listView.adapter = adapter

        biding.listView.setOnItemClickListener { adapterView, view, position, id ->
            val intent = Intent(this, HistoricoDetalhamentoActivity::class.java)
            intent.putExtra("HISTORICO", position)
            startActivity(intent)
        }
    }
}