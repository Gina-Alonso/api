package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;
    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroMedico datos){
        System.out.println(datos);
        repository.save(new Medico(datos));
    }
    @GetMapping
    public Page<MedicoDTO> listar(@PageableDefault(size = 10, sort= {"nombre"}) Pageable paginacion){
        return repository.findAll(paginacion).map(MedicoDTO::new);
    }

    @Transactional
    @PutMapping
    public void actualizar(@RequestBody @Valid MedicoDTOActualizado datos){
        var medico = repository.getReferenceById(datos.id());
        medico.actualizar(datos);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        repository.deleteById(id);

    }
}
