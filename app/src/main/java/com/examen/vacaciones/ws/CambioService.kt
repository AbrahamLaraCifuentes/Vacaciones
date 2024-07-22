package vacaciones.ws

import retrofit2.http.GET

interface CambioService {

    //https://www.mindicador.cl/api/dolar
    @GET("api/dolar")
    suspend fun getCambioDolar(): ListaCambio

}