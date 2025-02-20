package marceloviana1991.listadechamada

class ChamadaDao {

    companion object {
        private val chamadas = mutableListOf<Chamada>()

        fun adicionar(chamada: Chamada) {
            chamadas.add(chamada)
        }

        fun buscaTodas(): List<Chamada> {
            return chamadas
        }
    }
}