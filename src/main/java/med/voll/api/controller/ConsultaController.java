package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consultas.DatosDetalleConsultaDTO;
import med.voll.api.domain.consultas.DatosReservarConsulta;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid DatosReservarConsulta datos){
        System.out.println(datos);
        return ResponseEntity.ok(new DatosDetalleConsultaDTO(null, null, null, null));

    }
}
