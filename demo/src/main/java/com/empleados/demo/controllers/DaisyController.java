package com.empleados.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DaisyController {

    @GetMapping("/daisyui")
    public String daisyUi() {
        return "daisyui";
    }
}