package marceloviana1991.listadechamada

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import marceloviana1991.listadechamada.databinding.AdapterMainBinding

class MainAdapter(
    private val context: Context,
    private val pessoas: List<Pessoa>
): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: AdapterMainBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun vincula(pessoa: Pessoa) {
            binding.textViewNome.text = pessoa.nome
            binding.switchPresenca.isChecked = pessoa.presenca
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = AdapterMainBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = pessoas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pessoa = pessoas[position]
        holder.vincula(pessoa)
    }

}
