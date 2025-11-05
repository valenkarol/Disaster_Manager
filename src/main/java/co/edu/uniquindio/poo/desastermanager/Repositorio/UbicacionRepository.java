package co.edu.uniquindio.poo.desastermanager.Repositorio;


import co.edu.uniquindio.poo.desastermanager.Ubicacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepository extends MongoRepository<Ubicacion, String> {
}

