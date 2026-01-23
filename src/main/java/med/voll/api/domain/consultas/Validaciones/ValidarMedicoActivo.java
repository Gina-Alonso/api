package med.voll.api.domain.consultas.Validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consultas.DatosReservarConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoActivo implements ValidadorConsultas{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosReservarConsulta datos){
        if (datos.idMedico() == null) {
            return;
        }
        var medicoEstaActivo = medicoRepository.findActivoById(datos.idMedico());
        if (!medicoEstaActivo){
            throw new ValidacionException("Consulta no puede ser reservada con un medico inactivo");
        }
    }
}

