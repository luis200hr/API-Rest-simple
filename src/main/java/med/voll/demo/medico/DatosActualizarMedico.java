package med.voll.demo.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.demo.direccion.DatosDireccion;

public record DatosActualizarMedico(
		@NotNull
		Long id,
		String nombre,
		String documento,
		DatosDireccion direccion
		) {

}
