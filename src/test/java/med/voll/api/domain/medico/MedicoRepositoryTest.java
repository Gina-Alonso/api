package med.voll.api.domain.medico;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import javax.swing.text.html.parser.Entity;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("Deberia volver null cuando el medico existe pero no esta disponible")
    void elegirMedicoAleatorioDisponibleEnFechaEscenario1() {

        var lunessiguienteAlas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        /*var medico =
                var paciente =
                var consulta =
        var medicolibre = medicoRepository.elegirMedicoAleatorioDisponibleEnFecha(Especialidad.CARDIOLOGIA, lunessiguienteAlas10);
        assertThat(medicolibre).isNull();
        */

    }
}