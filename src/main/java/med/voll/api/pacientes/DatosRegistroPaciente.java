package med.voll.api.pacientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direccion.DatoDireccion;

public record DatosRegistroPaciente(
        @NotBlank String nombre,
        @NotBlank  String email,
        @NotBlank String telefono,
        @NotBlank @Pattern(regexp = "\\d{7,9}") String documento_identidad,
        @NotNull @Valid DatoDireccion direccion
) {
}
