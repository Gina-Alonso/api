package med.voll.api.domain.consultas.Validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consultas.DatosReservarConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class ValidacionConsultaAnticipacion implements ValidadorConsultas{

    public void validar(DatosReservarConsulta datos){
       var fechaConsulta = datos.fecha();
        var ahora = LocalDateTime.now();
        var diferenciaEnMinutos = Duration.between(ahora, fechaConsulta).toMinutes();
        if (diferenciaEnMinutos<30){
            throw new ValidacionException("No se puede agendar una cita antes de 30 min de anticipacion");
        }
    }
}
