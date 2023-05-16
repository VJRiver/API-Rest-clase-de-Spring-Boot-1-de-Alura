package med.voll.api.paciente;

import java.util.Objects;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documentoIdentidad;
    private String telefono;
    @Embedded
    private Direccion direccion;
    private boolean activo;

    public Paciente(@Valid DatosRegistroPaciente datosRegistroPaciente) {
        this.activo = true;
        this.nombre = datosRegistroPaciente.nombre();
        this.email = datosRegistroPaciente.email();
        this.documentoIdentidad = datosRegistroPaciente.documentoIdentidad();
        this.telefono = datosRegistroPaciente.telefono();
        this.direccion = new Direccion(datosRegistroPaciente.direccion());
    }

    public Paciente() {
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void desactivarPaciente(Paciente paciente) {
       this.activo = false;
        
    }
    
    public void actualizarDatos(DatosActualizarPaciente datosActualizarPaciente) {
        
        if(!Objects.isNull(datosActualizarPaciente.nombre())) {
            System.out.println("datosActualizarPaciente.nombre() " + datosActualizarPaciente.nombre());
            this.nombre = datosActualizarPaciente.nombre();
        }
        if(!Objects.isNull(datosActualizarPaciente.telefono())) {
            System.out.println("datosActualizarMedico.telefono() " + datosActualizarPaciente.telefono());
            this.telefono = datosActualizarPaciente.telefono();
        }
        System.out.println("direccion es nula? " + Objects.isNull(datosActualizarPaciente.direccion()));
        if(!Objects.isNull(datosActualizarPaciente.direccion())) {
            this.direccion = direccion.actualizarDatos(datosActualizarPaciente.direccion());
        }
    }
        
}





