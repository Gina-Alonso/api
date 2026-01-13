package med.voll.api.medico;

public record MedicoDTO(
        String nombre,
        String email,
        String documento,
        Especialidad especialidad
) {
    public MedicoDTO(Medico medico) {
        this(medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getEspecialidad());
    }
}
