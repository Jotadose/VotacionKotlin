package com.example.votacionkotlin

object VotingSystem{

    //listar los locales de votacion validos
    private val localesValidos = listOf("Local A", "Local B", "Local C", "Local D")

    @JvmStatic
    fun main(args: Array<String>) {
        val nombre = solicitarNombre()
        val edad = solicitarEdad()
        val tieneImpedimiento = solicitarImpedimento()
        val localDeVotacion = solicitarLocalVotacion()

        val resultado = puedeVotar(edad,tieneImpedimiento, localDeVotacion)

        mostrarResultado (nombre, resultado)

    }



    private fun solicitarNombre(): String {
        println("Ingrese tu nombre:")
        return readLine()?.trim().takeIf { !it.isNullOrEmpty() } ?: run {
            println("Nombre inválido, intenta nuevamente.")
            return solicitarNombre()

        }
    }

    private fun solicitarEdad(): Int {
        println("Ingresa tu edad:")
        val edad = readLine()?.toIntOrNull()
        return edad?.takeIf { it in 18..100 } ?: run {
            println("Edad inválida o fuera de rango permitido (18-100 años).")
            return solicitarEdad()
        }
    }

    // funcion para consultar si tiene impedimento para votar
    private fun solicitarImpedimento(): Boolean {
        println("¿Tienes algún impedimeiento para votar? (si/no)")
        val respuesta =  readLine()?.trim()?.lowercase()
        return when (respuesta) {
            "no" -> false
            "si" -> true
            else -> {
                println("respuesta no valida, por favor responder 'si' o 'no'.")
                return solicitarImpedimento()
            }
        }
    }

    // funcion para solicitar local de votacion
    private fun solicitarLocalVotacion(): String {
        println("Los locales disponibles son: ${localesValidos.joinToString(",")}")
        println("Ingresa tu local de votacion:")

        val local = readLine()?.trim()?.lowercase()
        return local?.let { localIngresado ->
            localesValidos.firstOrNull() { it.lowercase() == localIngresado } ?: run {
                println ("Local de votación no valido. Ingrese nuevamente ")
                return solicitarLocalVotacion()
            }
        } ?: run {
            println("Porfavor, ingresa un local valido.")
            return solicitarLocalVotacion()
        }
    }

    //funcion para volidad si peude votar
    private fun puedeVotar(edad: Int, tieneImpedimiento: Boolean, localDeVotacion: String): Boolean {
        return edad in 18..100 && !tieneImpedimiento && localDeVotacion in localesValidos
    }

    //funcion para mostrar resultado
    private fun mostrarResultado(nombre: String, puedeVotar: Boolean) {
        if (puedeVotar) {
            println("¡Hola $nombre! Puedes votar en el local asignado.")
        } else {
                println("Lo siento $nombre, no puedes votar")
            }
        }
    }
