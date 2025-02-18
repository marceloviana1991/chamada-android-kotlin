package marceloviana1991.listadechamada

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import marceloviana1991.listadechamada.databinding.ActivityChamadaBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ChamadaActivity : AppCompatActivity() {

    private val biding by lazy {
        ActivityChamadaBinding.inflate(layoutInflater)
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

        val agora = LocalDateTime.now()
        val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        val dataFormatada = agora.format(formato)
        title = "Lista de faltas - ${dataFormatada}"

        val chamada = intent.getStringArrayListExtra("CHAMADA")

        val recyclerView = biding.recyclerview

        recyclerView.adapter = chamada?.let {
            ChamadaAdapter(
                this,
                it
            )
        }

        val codigos = mutableListOf(
            "abcd"
        )

        val button = biding.Button
        button.setOnClickListener {
            val editText = biding.editText.text
            if(codigos.contains(editText.toString())) {
                Toast.makeText(this, "Chamada salva com sucesso!", Toast.LENGTH_SHORT)
                    .show()
                finish()
            } else {
                Toast.makeText(
                    this, "Código inválido!", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}