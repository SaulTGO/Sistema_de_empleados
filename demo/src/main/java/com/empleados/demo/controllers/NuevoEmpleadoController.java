package com.empleados.demo.controllers;

import org.springframework.stereotype.Controller;

import com.empleados.demo.domain.Empleado;
import com.empleados.demo.services.EmpleadoRepositoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller 
public class NuevoEmpleadoController {
    
    private final EmpleadoRepositoryService empleadoRepositoryService;

    public NuevoEmpleadoController(EmpleadoRepositoryService empleadoRepositoryService) {
        this.empleadoRepositoryService = empleadoRepositoryService;
    }

    @GetMapping("/nuevo-empleado")
    public String mostrarFormularioNuevoEmpleado() {
        return "nuevo-empleado";
    }
    

    @PostMapping("/nuevo-empleado")
    public String nuevoEmpleado(RedirectAttributes redirectAttributes,
        @RequestParam String nombre,
        @RequestParam String apellido,
        @RequestParam String email
    ) {
        Empleado nuevoEmpleado = new Empleado(nombre, apellido, email);

        empleadoRepositoryService.nuevoEmpleado(nuevoEmpleado);
        redirectAttributes.addFlashAttribute("mensaje", "Empleado guardado correctamente");

        return "redirect:/Empleados";
    }
    
}
