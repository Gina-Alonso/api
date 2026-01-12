package med.voll.api.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Direccion {
    private String calle;
    private String numero;
    private String complemento;
    private String barrio;
    private String codigo_postal;
    private String ciudad;
    private String estado;

    public Direccion(DatoDireccion datoDireccion) {
        this.calle = datoDireccion.calle();
        this.numero = datoDireccion.numero();
        this.complemento = datoDireccion.complemento();
        this.barrio = datoDireccion.barrio();
        this.codigo_postal = datoDireccion.codigo_postal();
        this.ciudad = datoDireccion.ciudad();
        this.estado = datoDireccion.estado();
    }
}
