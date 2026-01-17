package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatoDireccion;

public record MedicoDTOActualizado(
        @NotNull Long id,
        String nombre,
        String telefono,
        DatoDireccion direccion
) {
}
