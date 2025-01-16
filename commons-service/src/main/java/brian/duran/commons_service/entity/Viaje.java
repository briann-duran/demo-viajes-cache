package brian.duran.commons_service.entity;

import brian.duran.commons_service.enums.Provincia;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
