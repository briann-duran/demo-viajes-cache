package brian.duran.puntos_de_ventas_services.repository;

import brian.duran.commons_service.entity.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
    @Query("SELECT u FROM Ubicacion u WHERE u.nombre = :nombre")
    Optional<Ubicacion> findByNombre(String nombre);
}
