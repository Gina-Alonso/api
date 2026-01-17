package med.voll.api.domain.medico;

import med.voll.api.domain.direccion.Direccion;

public record DatosDetalleMedicoDTO(
        Long id,
        String nombre,
        String email,
        String documento,
        String telefono,
        Especialidad especialidad,
        Direccion direccion
) {
    public DatosDetalleMedicoDTO(Medico medico){
        this(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getTelefono(), medico.getEspecialidad(), medico.getDireccion());
    }
}
