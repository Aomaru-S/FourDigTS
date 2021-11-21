package com.rinats.fourdigst

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@Controller
class FourDigTSController(
    @Autowired
    private val digTextRepository: DigTextRepository
) {
    @GetMapping("/")
    fun index(): String {
        return "index"
    }

    @PostMapping("/perform_share")
    fun performShare(
        response: HttpServletResponse,
        session: HttpSession,
        @RequestParam text: String
    ): String {
        val digits = (0..9999).random().toString().padStart(4, '0')
        val expire = Calendar.getInstance()
        expire.add(Calendar.MINUTE, 1)
        val digText = DigText(digits, text, expire.time)
        digTextRepository.save(digText)
        session.setAttribute("digits", digits)
        session.setAttribute("text", text)
        return "redirect:/result"
    }

    @GetMapping("/result")
    fun result(
        request: HttpServletRequest,
        model: Model,
        @SessionAttribute digits: String,
        @SessionAttribute text: String
    ): String {
        model.addAttribute("digits", digits)
        model.addAttribute("text", text)
        return "result"
    }

    @GetMapping("/{digits:[0-9]{4}}")
    fun showText(@PathVariable digits: String): String {
        val text = digTextRepository.findById(digits).orElse(null) ?: return "index"
        println(text)
        return "index"
    }
}