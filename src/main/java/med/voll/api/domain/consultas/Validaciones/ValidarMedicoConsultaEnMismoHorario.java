package med.voll.api.domain.consultas.Validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consultas.ConsultaRepository;
import med.voll.api.domain.consultas.DatosReservarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoConsultaEnMismoHorario implements ValidadorConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosReservarConsulta datos){
        var medicoTieneOtraConsultaEnMismoHorario = consultaRepository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());
        if (medicoTieneOtraConsultaEnMismoHorario){
            throw new ValidacionException("Medico ya tiene ptra consulta en el mismo horario");
        }
    }
}
