package med.voll.api.medico;

public record MedicoDTO(
        Long id,
        String nombre,
        String email,
        String documento,
        String telefono,
        Especialidad especialidad
) {
    public MedicoDTO(Medico medico) {
        this(medico.getId(),medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getTelefono(), medico.getEspecialidad());
    }
}
