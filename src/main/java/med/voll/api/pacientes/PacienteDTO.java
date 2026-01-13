package med.voll.api.pacientes;

public record PacienteDTO(String nombre, String email, String documentoIdentidad) {
    public PacienteDTO(Paciente paciente) {
        this(paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoIdentidad());
    }
}

