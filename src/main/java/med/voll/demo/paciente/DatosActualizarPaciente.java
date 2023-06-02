package med.voll.demo.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.demo.direccion.DatosDireccion;

public record DatosActualizarPaciente(
		@NotNull
		Long id,
		String nombre,
		String documentoIdentidad,
		DatosDireccion direccion
		) {

}
