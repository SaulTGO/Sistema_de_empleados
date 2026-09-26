package com.empleados.demo.controllers;

import com.empleados.demo.domain.Empleado;
import com.empleados.demo.services.EmpleadoRepositoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("/empleado")
@Controller
public class EmpleadoController {

    private final EmpleadoRepositoryService empleadoRepositoryService;

    public EmpleadoController(EmpleadoRepositoryService empleadoRepositoryService) {
        this.empleadoRepositoryService = empleadoRepositoryService;
    }

    @GetMapping
    public String empleados(Model model) {
        model.addAttribute("empleados", empleadoRepositoryService.findAllEmpleados());
        return "empleados";
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

        return "redirect:/empleado";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarEmpleado(@PathVariable Long id, Model model) {
        Empleado empleado = empleadoRepositoryService.buscarEmpleado(id)
            .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        model.addAttribute("empleado", empleado);
        return "editar-empleado";
    }

    @PostMapping("/{id}/editar")
    public String editarEmpleado(
        RedirectAttributes redirectAttributes,
        @PathVariable Long id,
        @RequestParam String nombre,
        @RequestParam String apellido,
        @RequestParam String email
    ) {
        Empleado empleado = empleadoRepositoryService.buscarEmpleado(id)
            .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setEmail(email);
        empleadoRepositoryService.nuevoEmpleado(empleado);

        redirectAttributes.addFlashAttribute("mensaje", "Empleado editado correctamente");
        return "redirect:/empleado";
    }

    @PostMapping("/{id}/eliminar")
    public String borrarEmpleado(RedirectAttributes redirectAttributes,
        @PathVariable Long id){
        
            try{
                empleadoRepositoryService.eliminarEmpleado(id);
                redirectAttributes.addFlashAttribute("mensaje", "Empleado eliminado correctamente");
            }catch(Exception e){
                redirectAttributes.addFlashAttribute("mensaje", "Error al eliminar el empleado");
            }
        return "redirect:/empleado";
    }
    
}