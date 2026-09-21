package com.empleados.demo.controllers;

import com.empleados.demo.services.EmpleadoRepositoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class EmpleadosController {

    private final EmpleadoRepositoryService empleadoRepositoryService;

    public EmpleadosController(EmpleadoRepositoryService empleadoRepositoryService) {
        this.empleadoRepositoryService = empleadoRepositoryService;
    }

    @GetMapping({"/empleados", "/Empleados"})
    public String empleados(Model model) {
        model.addAttribute("empleados", empleadoRepositoryService.findAllEmpleados());
        return "empleados";
    }
    
}