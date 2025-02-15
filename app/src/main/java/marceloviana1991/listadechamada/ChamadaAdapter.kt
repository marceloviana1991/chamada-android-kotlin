package marceloviana1991.listadechamada

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import marceloviana1991.listadechamada.databinding.AdapterChamadaBinding

class ChamadaAdapter(
    private val context: Context,
    val chamada: List<String>
) : RecyclerView.Adapter<ChamadaAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: AdapterChamadaBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun vincula(nome: String) {
            binding.textView.text = nome
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = AdapterChamadaBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = chamada.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nome = chamada[position]
        holder.vincula(nome)
    }

}
