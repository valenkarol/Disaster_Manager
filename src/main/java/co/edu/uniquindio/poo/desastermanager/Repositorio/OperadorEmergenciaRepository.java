package co.edu.uniquindio.poo.desastermanager.Repositorio;

import co.edu.uniquindio.poo.desastermanager.Modelo.OperadorEmergencia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperadorEmergenciaRepository extends MongoRepository<OperadorEmergencia, String> {
    Optional<OperadorEmergencia> findByEmail(String email);
}
