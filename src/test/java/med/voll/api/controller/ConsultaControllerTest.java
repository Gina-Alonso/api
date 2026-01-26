package med.voll.api.controller;

import med.voll.api.domain.consultas.DatosDetalleConsultaDTO;
import med.voll.api.domain.consultas.DatosReservarConsulta;
import med.voll.api.domain.consultas.ReservaDeConsultas;
import med.voll.api.domain.medico.Especialidad;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<DatosReservarConsulta> datosReservarConsultaJacksonTester;
    @Autowired
    private JacksonTester<DatosDetalleConsultaDTO> datosDetalleConsultaDTOJacksonTester;
    @Test
    @DisplayName("Deberia devolver metodo http 400 cuando la request no tenga datos")
    @WithMockUser
    void reservar_escenario1() throws Exception {
        var response = mockMvc.perform(post("/consultas"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @MockitoBean
    private ReservaDeConsultas reservaDeConsultas;

    @Test
    @DisplayName("Deberia devolver metodo http 200 cuando la request reciba un json value")
    @WithMockUser
    void reservar_escenario2() throws Exception {
        var fecha = LocalDateTime.now().plusHours(1);
        var especialidad = Especialidad.CARDIOLOGIA;
        var datosDetalle = new DatosDetalleConsultaDTO(null, 2l, 5l, fecha);
        when(reservaDeConsultas.reservar(any())).thenReturn(datosDetalle);

        var response = mockMvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(datosReservarConsultaJacksonTester.write(
                                new DatosReservarConsulta(2l, 5l,fecha, especialidad)
                        ).getJson()))
                .andReturn().getResponse();
        var jsonEsperado = datosDetalleConsultaDTOJacksonTester.write(new DatosDetalleConsultaDTO(null, 2l, 5l, fecha)).getJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}