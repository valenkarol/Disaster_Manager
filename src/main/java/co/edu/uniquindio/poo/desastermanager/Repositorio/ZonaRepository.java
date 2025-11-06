package co.edu.uniquindio.poo.desastermanager.Repositorio;

import co.edu.uniquindio.poo.desastermanager.Modelo.Zona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaRepository extends MongoRepository<Zona, String> {
}
