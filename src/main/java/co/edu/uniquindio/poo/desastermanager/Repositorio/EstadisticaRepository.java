package co.edu.uniquindio.poo.desastermanager.Repositorio;

import co.edu.uniquindio.poo.desastermanager.Modelo.Estadistica;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadisticaRepository extends MongoRepository<Estadistica, String> {
    // Podrías agregar consultas específicas si lo necesitas
}
