package co.edu.uniquindio.poo.desastermanager.Repositorio;

import co.edu.uniquindio.poo.desastermanager.Modelo.Evacuacion;
import co.edu.uniquindio.poo.desastermanager.Modelo.Zona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvacuacionRepository extends MongoRepository<Evacuacion, String> {

    // Cuenta las evacuaciones de una zona que ya fueron procesadas
    long countByZonaAndProcesadaTrue(Zona zona);

    // Cuenta las evacuaciones de una zona que aún no han sido procesadas
    long countByZonaAndProcesadaFalse(Zona zona);
}

