package marceloviana1991.listadechamada

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import marceloviana1991.listadechamada.databinding.AdapterMainBinding

class MainAdapter(
    private val context: Context,
    nomes: List<String>,
    var quandoClicaNoItemListener: (ativo: String) -> Unit = {},
): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val nomes = nomes.toMutableList()
    private val ausentes = nomes.toMutableList()

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
                    ausentes.remove(binding.textViewNome.text.toString())
                } else {
                    ausentes.add(binding.textViewNome.text.toString())
                }
            }
        }
        fun vincula(nome: String, posicao: Int) {
            binding.textViewNome.text = nome
            binding.switchPresenca.isChecked = false
            val posicao = posicao + 1
            binding.textViewNumero.text = "$posicao - "
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
        holder.vincula(nome, position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun atualiza(nomes: List<String>) {
        this.nomes.clear()
        this.nomes.addAll(nomes)
        this.ausentes.clear()
        this.ausentes.addAll(nomes)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun finaliza(): List<String> {
        notifyDataSetChanged()
        return ausentes
    }

}
