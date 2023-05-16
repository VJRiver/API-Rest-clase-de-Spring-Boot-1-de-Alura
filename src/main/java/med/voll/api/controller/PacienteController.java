package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.paciente.DatosActualizarPaciente;
import med.voll.api.paciente.DatosListadoPaciente;
import med.voll.api.paciente.DatosRegistroPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    
    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public void registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente) {
        pacienteRepository.save(new Paciente(datosRegistroPaciente));
    }
    
    @GetMapping
    public Page<DatosListadoPaciente> listadoPacientes(@PageableDefault(size = 10)Pageable paginacion){
        return pacienteRepository.findByActivoTrue(paginacion).map(DatosListadoPaciente::new);
        }
    
    @DeleteMapping("{id}")
    @Transactional
    public void eliminarPaciente(@PathVariable Long id) {
        
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.desactivarPaciente(paciente);
        
    }
    
    @PutMapping
    @Transactional
    public void actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente datosActualizarPaciente) {
        
        Paciente paciente = pacienteRepository.getReferenceById(datosActualizarPaciente.id());
        System.out.println("Valor de paciente en actualizarPaciente: " + paciente.getNombre() + " con id " + paciente.getId());
        paciente.actualizarDatos(datosActualizarPaciente);
    }
}















