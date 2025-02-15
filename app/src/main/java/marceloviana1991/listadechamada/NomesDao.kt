package marceloviana1991.listadechamada

class NomesDao {

    companion object {
        private val nomes = mutableListOf<String>()

        fun adicionar(nome: String) {
            nomes.add(nome)
        }

        fun buscarTodos(): List<String> {
            return nomes
        }

        fun excluir(nome: String?) {
            nomes.remove(nome)
        }
    }
}
