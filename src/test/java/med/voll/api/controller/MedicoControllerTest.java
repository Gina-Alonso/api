package med.voll.api.controller;

import med.voll.api.domain.consultas.DatosReservarConsulta;
import med.voll.api.domain.direccion.DatoDireccion;
import med.voll.api.domain.direccion.Direccion;
import med.voll.api.domain.medico.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DatosRegistroMedico> datosRegistroMedicoJacksonTester;

    @Autowired
    private JacksonTester<DatosDetalleMedicoDTO> datosDetalleMedicoDTOJacksonTester;

    @MockitoBean
    private MedicoRepository medicoRepository;


    @Test
    @DisplayName("Deberia devolver codigo http 400 cuando la informacion es invalida")
    @WithMockUser
    void registrar_escenario1() throws Exception {
        var response = mockMvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }


    @Test
    @DisplayName("Debería devolver código http 200 cuando las informaciones son válidas")
    @WithMockUser
    void registrar_escenario2() throws Exception {
        var datosRegistro = new DatosRegistroMedico(
                "Medico",
                "medico@voll.med",
                "619999999",
                "12345678",
                Especialidad.CARDIOLOGIA,
                datosDireccion());

        when(medicoRepository.save(any())).thenReturn(new Medico(datosRegistro));

        var response = mockMvc.perform(post("/medicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(datosRegistroMedicoJacksonTester.write(datosRegistro).getJson()))
                .andReturn().getResponse();

        var datosDetalle= new DatosDetalleMedicoDTO(
                null,
                datosRegistro.nombre(),
                datosRegistro.email(),
                datosRegistro.documento(),
                datosRegistro.telefono(),
                datosRegistro.especialidad(),
                new Direccion(datosRegistro.direccion())
        );
        var jsonEsperado = datosDetalleMedicoDTOJacksonTester.write(datosDetalle).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
    private DatoDireccion datosDireccion() {
        return new DatoDireccion(
                "calle ejemplo",
                "125",
                "Co plemento",
                "Barrio",
                "8627",
                "Mendoza",
                "Estado"
        );
    }
}