package med.voll.api.domain.pacientes;

public record PacienteDTO(Long id,String nombre, String email, String documentoIdentidad) {
    public PacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoIdentidad());
    }
}

