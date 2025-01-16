package brian.duran.commons_service.dto;

import brian.duran.commons_service.enums.Provincia;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class UbicacionDto implements Serializable {
    private static final long serialVersionUID = 1L; // Versión única de la clase.

    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    @NotNull(message = "El nombre de la ubicación debe ser obligatorio.")
    private Provincia nombre;
}
