package co.edu.uniquindio.poo.desastermanager.Repositorio;

import co.edu.uniquindio.poo.desastermanager.Modelo.Desastre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesastreRepository extends MongoRepository<Desastre, String> {
}
