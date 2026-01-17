package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;
    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroMedico datos, UriComponentsBuilder uriComponentsBuilder){
        System.out.println(datos);
        var medico = new Medico(datos);
        repository.save(medico);
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleMedicoDTO(medico));
    }
    @GetMapping
    public ResponseEntity<Page<MedicoDTO>> listar(@PageableDefault(size = 10, sort= {"nombre"}) Pageable paginacion){
        var page= repository.findAllByActivoTrue(paginacion).map(MedicoDTO::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid MedicoDTOActualizado datos){
        var medico = repository.getReferenceById(datos.id());
        medico.actualizar(datos);
        return ResponseEntity.ok(new DatosDetalleMedicoDTO(medico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        //repository.deleteById(id); -> eliminacion fisica
        var medico = repository.getReferenceById(id);
        medico.eliminar();//eliminacion logica
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleMedicoDTO(medico));
    }
}
