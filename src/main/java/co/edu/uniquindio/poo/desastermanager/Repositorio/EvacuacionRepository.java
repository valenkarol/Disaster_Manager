package co.edu.uniquindio.poo.desastermanager.Repositorio;

import co.edu.uniquindio.poo.desastermanager.Modelo.Evacuacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvacuacionRepository extends MongoRepository<Evacuacion,String> {

}
