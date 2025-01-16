package brian.duran.commons_service.dto;

import brian.duran.commons_service.enums.Provincia;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AcreditacionDto implements Serializable {
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
