package co.edu.uniquindio.poo.desastermanager.Repositorio;

import co.edu.uniquindio.poo.desastermanager.Modelo.Informe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformeRepository extends MongoRepository<Informe,String> {
}
