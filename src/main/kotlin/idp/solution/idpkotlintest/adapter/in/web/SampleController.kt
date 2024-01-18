package idp.solution.idpkotlintest.adapter.`in`.web

import idp.solution.idpkotlintest.application.SampleRetrofitService
import idp.solution.idpkotlintest.application.port.`in`.LoginCheckUseCase
import idp.solution.idpkotlintest.util.RetrofitConnection
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response

@RestController
@RequestMapping("/api/sample")
class SampleController {

    val log: Logger = LoggerFactory.getLogger(SampleController::class.java)

    @GetMapping
    fun sample(): LoginCheckUseCase.Result {
        val retrofitApi = RetrofitConnection
            .getInstance("http://localhost:8080/")
            .create(SampleRetrofitService::class.java)
            .test()
            .execute()
//          execute 대신에 enqueue 는 비동기
//            .enqueue(object: Callback<String> {
//                override fun onResponse(p0: Call<String>, p1: Response<String>) {
//                }
//
//                override fun onFailure(p0: Call<String>, p1: Throwable) {
//                }
//            })

        return LoginCheckUseCase.Result(email = retrofitApi.body().orEmpty(), success = retrofitApi.isSuccessful)
    }

    @GetMapping("/test")
    fun test(): String = "test"
}