package med.voll.api.domain.consultas;

import jakarta.validation.Valid;
import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consultas.Validaciones.ValidadorCancelarConsultas;
import med.voll.api.domain.consultas.Validaciones.ValidadorConsultas;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    List<ValidadorConsultas> validadores;

    @Autowired
    List<ValidadorCancelarConsultas> validadoresCancelarConsultas;

    public DatosDetalleConsultaDTO reservar(DatosReservarConsulta datos) {
        if (!pacienteRepository.existsById(datos.idPaciente())){
            throw new ValidacionException("No existe un paciente con el id informado");
        }
        if (datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionException("No existe un medico con el id informado");
        }

        validadores.forEach(v->v.validar(datos));

        var medico = elegirMedico(datos);
        if (medico==null){
            throw new ValidacionException("No existe ningun medico disponible en ese horario");
        }
        var paciente =  pacienteRepository.findById(datos.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, datos.fecha(), null);
        repository.save(consulta);
        return new DatosDetalleConsultaDTO(consulta);
    }

    private Medico elegirMedico(DatosReservarConsulta datos) {
        if (datos.idMedico() != null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad() == null){
            throw new ValidacionException("Esnecesario elegir una especialidad cuando no se elige medico");
        }
        return medicoRepository.elegirMedicoAleatorioDisponibleEnFecha(datos.especialidad(), datos.fecha());
    }

    public void cancelar(@Valid DatosCancelarConculta datos) {
        if (!repository.existsById(datos.idConsulta())){
            throw new ValidacionException("Id de la consulta informada no existe");
        }
        validadoresCancelarConsultas.forEach(v->v.validar(datos));
        var consulta = repository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }
}
