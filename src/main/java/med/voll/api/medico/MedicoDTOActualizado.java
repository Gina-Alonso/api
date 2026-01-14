package med.voll.api.medico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.direccion.DatoDireccion;
import med.voll.api.direccion.Direccion;

public record MedicoDTOActualizado(
        @NotNull Long id,
        String nombre,
        String telefono,
        DatoDireccion direccion
) {
}
