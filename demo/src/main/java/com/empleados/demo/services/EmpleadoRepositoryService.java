package com.empleados.demo.services;


import org.springframework.stereotype.Service;
import com.empleados.demo.repository.EmpleadoRepository;
import com.empleados.demo.domain.Empleado;
import java.util.List;
import java.util.Optional;

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

    public Optional<Empleado> buscarEmpleado(Long id){
        return empleadoRepository.findById(id);
    }

    public void eliminarEmpleado(Long id){
        empleadoRepository.deleteById(id);
    }

}
