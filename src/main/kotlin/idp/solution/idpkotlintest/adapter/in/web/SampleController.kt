package idp.solution.idpkotlintest.adapter.`in`.web

import idp.solution.idpkotlintest.application.port.`in`.LoginCheckUseCase
import idp.solution.idpkotlintest.application.port.`in`.SampleRetrofitService
import idp.solution.idpkotlintest.util.RetrofitConnection
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        return LoginCheckUseCase.Result(email = retrofitApi.body().orEmpty(), success = retrofitApi.isSuccessful)
    }

    @GetMapping("/test")
    fun test(): String = "test"

    @GetMapping("/mariadb/products")
    fun mariadbProducts(): String {
        RetrofitConnection
            .getInstance("https://downloads.mariadb.org/")
            .create(SampleRetrofitService::class.java)
            .mariadbProducts()
            .enqueue(object: Callback<SampleRetrofitService.Result> {
                override fun onResponse(p0: Call<SampleRetrofitService.Result>, p1: Response<SampleRetrofitService.Result>) {
                    log.info(p1.body().toString())
                }

                override fun onFailure(p0: Call<SampleRetrofitService.Result>, p1: Throwable) {
                    log.info(">>>>>>>${p1.message}")
                }
            })

        return "success!"
    }
}