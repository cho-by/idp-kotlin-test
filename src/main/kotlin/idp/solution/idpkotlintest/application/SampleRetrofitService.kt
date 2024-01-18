package idp.solution.idpkotlintest.application

import retrofit2.Call
import retrofit2.http.GET

interface SampleRetrofitService {
    @GET("/api/sample/test")
    fun test(): Call<String>
}