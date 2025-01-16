package brian.duran.commons_service.dto;

import brian.duran.commons_service.enums.Provincia;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class ViajeDto implements Serializable {
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    @NotNull(message = "El nombre de la ubicación debe ser obligatorio.")
    private Provincia provinciaPartida;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    @NotNull(message = "El nombre de la ubicación debe ser obligatorio.")
    private Provincia provinciaLlegada;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor que cero")
    private double costo;
}
