package med.voll.api.domain.consultas.Validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consultas.DatosReservarConsulta;
import med.voll.api.domain.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarPacienteActivo implements ValidadorConsultas{

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DatosReservarConsulta datos){
        var pacienteEstaActivo = pacienteRepository.findActivoById(datos.idPaciente());
        if (!pacienteEstaActivo){
            throw new ValidacionException("Consulta no puede ser reservada con un paciente inactivo");
        }
    }
}
