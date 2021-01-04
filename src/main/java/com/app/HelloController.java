/*
 * package com.app; import org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.ResponseBody; import
 * org.springframework.web.servlet.ModelAndView;
 * 
 * @Controller public class HelloController {
 * 
 * @GetMapping({"/", "/templates/{name}"}) public @ResponseBody String
 * hello(Model model, @PathVariable("name") String name) { ModelAndView
 * modelAndView = new ModelAndView("redirect:/"+name);
 * model.addAttribute("name", name); return name; } }
 */