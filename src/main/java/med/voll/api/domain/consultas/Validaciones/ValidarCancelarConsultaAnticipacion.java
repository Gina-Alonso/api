package med.voll.api.domain.consultas.Validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consultas.ConsultaRepository;
import med.voll.api.domain.consultas.DatosCancelarConculta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarCancelarConsultaAnticipacion implements ValidadorCancelarConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;
    @Override
    public void validar(DatosCancelarConculta datos) {
        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        var ahora = LocalDateTime.now();
        var diferenciaDeHoras = Duration.between(ahora, consulta.getFecha()).toHours();
        if (diferenciaDeHoras<24){
            throw new ValidacionException("La consulta solo se puede cancelar despues de 24 horas");
        }
    }
}
