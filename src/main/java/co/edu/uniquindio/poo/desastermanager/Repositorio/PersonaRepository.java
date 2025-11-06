package co.edu.uniquindio.poo.desastermanager.Repositorio;


import co.edu.uniquindio.poo.desastermanager.Modelo.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends MongoRepository<Persona,String> {

}
