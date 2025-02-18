package marceloviana1991.listadechamada

class NomesDao {

    companion object {
        private val nomes = mutableListOf<String>(
            "Allana Antonella Rosângela Rezende",
            "Victor Iago Francisco Rezende",
            "Letícia Silvana Silva",
           "Murilo Thiago Rafael Pereira",
            "Sérgio Nathan Gustavo Brito",
            "Isabella Benedita da Luz",
            "Débora Fabiana Ferreira",
            "Francisca Márcia Stefany Mendes",
            "Camila Adriana Fogaça"
        )

        fun adicionar(nome: String) {
            nomes.add(nome)
        }

        fun buscarTodos(): List<String> {
            return nomes.sorted()
        }

        fun excluir(nome: String?) {
            nomes.remove(nome)
        }
    }
}
