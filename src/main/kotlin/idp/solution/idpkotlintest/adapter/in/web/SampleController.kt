package idp.solution.idpkotlintest.adapter.`in`.web

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/sample")
class SampleController {

    @GetMapping
    fun sample():String {
        return "sample test"
    }
}