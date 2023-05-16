package med.voll.api.medico;

import java.util.Objects;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;

@Table(name = "medicos")
@Entity(name = "Medico")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String email;
    private String documento;
    private String telefono;
    private boolean activo;
    @Enumerated(EnumType.STRING)
    Especialidad especialidad;
    @Embedded
    Direccion direccion;
    
    /**
     * Spring me pide generar un constructor por defecto para que pueda ejecutar el método GET
     */
     public Medico() {

    }
    

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.activo = true;
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.documento = datosRegistroMedico.documento();
        this.telefono = datosRegistroMedico.telefono();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());
    }
    
    

    public long getId() {
        return id;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
        System.out.println("datosActualizarMedico en actualizarDatos: " + datosActualizarMedico);
        if(!Objects.isNull(datosActualizarMedico.nombre())) {
            System.out.println("datosActualizarMedico.nombre() " + datosActualizarMedico.nombre());
            this.nombre = datosActualizarMedico.nombre();
            System.out.println("El objeto this es " + this.getClass().getName());
        }
        if(!Objects.isNull(datosActualizarMedico.documentos())) {
            System.out.println("datosActualizarMedico.documentos() " + datosActualizarMedico.documentos());
            this.documento = datosActualizarMedico.documentos();
        }
        System.out.println("direccion es nula? " + Objects.isNull(datosActualizarMedico.direccion()));
        if(!Objects.isNull(datosActualizarMedico.direccion())) {
            this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
        }

    }


    public void desactivarMedico() {
        this.activo = false;
    }      
}












