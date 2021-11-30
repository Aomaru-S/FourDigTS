package com.rinats.fourdigst

import org.apache.commons.httpclient.HttpStatus
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/error")
class FourDigTSErrorController : ErrorController {
    @RequestMapping(produces = [MediaType.TEXT_HTML_VALUE])
    fun error(
        response: HttpServletResponse,
        model: Model
    ): String {
        val message = HttpStatus.getStatusText(response.status)
        model.addAttribute("code", response.status)
        model.addAttribute("message", message)
        return "error"
    }
}