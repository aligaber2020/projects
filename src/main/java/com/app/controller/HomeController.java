package com.app.controller;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HomeController
{

    @RequestMapping("/")
//    @PreAuthorize("hasAuthority('Root_Access')")
    public String home()
    {
        return "redirect:swagger-ui.html";
    }

}
