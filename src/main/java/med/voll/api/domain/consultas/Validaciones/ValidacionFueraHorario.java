package med.voll.api.domain.consultas.Validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consultas.DatosReservarConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ValidacionFueraHorario implements ValidadorConsultas{

    public void validar(DatosReservarConsulta datos ){
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horaAntesApertura = fechaConsulta.getHour()<7;
        var horaDespuesApertura = fechaConsulta.getHour()>18;
        if( domingo || horaAntesApertura || horaDespuesApertura){
            throw new ValidacionException("Horario seleccionado fuera de atendimiento de la clinica");
        }
    }
}
