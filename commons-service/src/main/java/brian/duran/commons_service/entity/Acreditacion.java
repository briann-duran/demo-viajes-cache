package brian.duran.commons_service.entity;

import brian.duran.commons_service.enums.Provincia;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Acreditacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor que cero")
    private Double importe;

    @Column(name = "id_ubicacion", unique = true)
    private Long idUbicacion;

    @JsonFormat(pattern = "ddMMyyyy HH:mm:ss")
    @Column(name = "fecha_de_recepcion")
    private LocalDateTime fechaRecepcion;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    @NotNull(message = "El nombre de la ubicación debe ser obligatorio.")
    private Provincia nombreUbicacion;
}
