package med.voll.api.pacientes;

import med.voll.api.domain.direccion.Direccion;
import med.voll.api.domain.pacientes.Paciente;

public record DatosDetallePacienteDTO(String nombre, String email, String telefono, String documentoIdentidad, Direccion direccion) {
    public DatosDetallePacienteDTO(Paciente paciente) {
        this(paciente.getNombre(), paciente.getEmail(), paciente.getTelefono(), paciente.getDocumentoIdentidad(), paciente.getDireccion());
    }
}
