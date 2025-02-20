package marceloviana1991.listadechamada

import java.time.LocalDateTime

data class Chamada(
    val dateTime: LocalDateTime,
    val codigoDoResponsavel: String,
    val ausentes: ArrayList<String>
)