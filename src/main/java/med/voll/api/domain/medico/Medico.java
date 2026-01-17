package med.voll.api.domain.medico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;

@Entity(name = "Medico")
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean activo;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico registroMedico) {
        this.id = null;
        this.activo = true;
        this.nombre = registroMedico.nombre();
        this.email = registroMedico.email();
        this.telefono = registroMedico.telefono();
        this.documento = registroMedico.documento();
        this.especialidad = registroMedico.especialidad();
        this.direccion = new Direccion(registroMedico.direccion());
    }

    public void actualizar(@Valid MedicoDTOActualizado dtoActualizado) {
        if(dtoActualizado.nombre() != null){
            this.nombre = dtoActualizado.nombre();
        }
        if(dtoActualizado.telefono() != null){
            this.telefono = dtoActualizado.telefono();
        }
        if (dtoActualizado.direccion()!= null){
            this.direccion.actualizarDireccion(dtoActualizado.direccion());
        }
    }

    public void eliminar() {
        this.activo = false;
    }
}