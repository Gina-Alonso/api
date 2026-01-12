package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;

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
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico registroMedico) {
        this.nombre = registroMedico.nombre();
        this.email = registroMedico.email();
        this.telefono = registroMedico.telefono();
        this.documento = registroMedico.documento();
        this.especialidad = registroMedico.especialidad();
        this.direccion = new Direccion(registroMedico.direccion());
    }
}