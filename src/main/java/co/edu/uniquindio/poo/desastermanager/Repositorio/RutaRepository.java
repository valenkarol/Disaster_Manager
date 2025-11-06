package co.edu.uniquindio.poo.desastermanager.Repositorio;

import co.edu.uniquindio.poo.desastermanager.Modelo.Ruta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutaRepository extends MongoRepository<Ruta, String> {
}
