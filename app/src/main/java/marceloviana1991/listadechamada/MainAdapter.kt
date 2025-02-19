package marceloviana1991.listadechamada

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import marceloviana1991.listadechamada.databinding.AdapterMainBinding
import kotlin.collections.ArrayList

class MainAdapter(
    private val context: Context,
    nomes: List<String>,
    var quandoClicaNoItemListener: (ativo: String) -> Unit = {},
): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val nomes = nomes.toMutableList()
    private val presencas = ArrayList<String>()

    inner class ViewHolder(
        private val binding: AdapterMainBinding
    ): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnLongClickListener {
                    PopupMenu(context, itemView).apply {
                        menuInflater.inflate(R.menu.menu_excluir, menu)
                        setOnMenuItemClickListener {
                            quandoClicaNoItemListener(binding.textViewNome.text.toString())
                            true
                        }
                    }.show()
                true
            }
            binding.switchPresenca.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    presencas.add(binding.textViewNome.text.toString())
                } else {
                    presencas.remove(binding.textViewNome.text.toString())
                }
            }
        }
        fun vincula(nome: String) {
            binding.textViewNome.text = nome
            binding.switchPresenca.isChecked = false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = AdapterMainBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = nomes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nome = nomes[position]
        holder.vincula(nome)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun atualiza(nomes: List<String>) {
        this.nomes.clear()
        this.nomes.addAll(nomes)
        notifyDataSetChanged()
    }

    fun finaliza(): ArrayList<String> {
        val faltas = ArrayList<String>()
        nomes.forEach {
            if (!presencas.contains(it)) {
                faltas.add(it)
             }
        }
        return faltas
    }

}
