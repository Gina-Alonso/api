package med.voll.api.domain.pacientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatoDireccion;

public record PacienteDTOActualizado(
        @NotNull Long id,
        String nombre,
        String telefono,
        @Valid DatoDireccion direccion
) {
}
