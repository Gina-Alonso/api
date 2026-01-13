package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.pacientes.DatosRegistroPaciente;
import med.voll.api.pacientes.Paciente;
import med.voll.api.pacientes.PacienteDTO;
import med.voll.api.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    PacienteRepository pacienteRepository;
    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroPaciente datos) {
        System.out.println("datos recibidos: " +datos);
        pacienteRepository.save(new Paciente(datos));
    }

    @GetMapping
    public Page<PacienteDTO> listar(@PageableDefault(page = 0, size = 10, sort = {"nombre"}) Pageable paginacion) {
        return pacienteRepository.findAll(paginacion).map(PacienteDTO::new);
    }
}
