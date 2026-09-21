package com.empleados.demo.services;


import org.springframework.stereotype.Service;
import com.empleados.demo.repository.EmpleadoRepository;
import com.empleados.demo.domain.Empleado;
import java.util.List;


@Service 
public class EmpleadoRepositoryService{

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoRepositoryService(EmpleadoRepository empleadoRepository){
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleado> findAllEmpleados(){
        return empleadoRepository.findAll();
    }

    public void nuevoEmpleado (Empleado empleado){
        empleadoRepository.save(empleado);
    }

}
