package med.voll.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.demo.medico.DatosActualizarMedico;
import med.voll.demo.medico.Medico;
import med.voll.demo.paciente.DatosActualizarPaciente;
import med.voll.demo.paciente.DatosListadoPaciente;
import med.voll.demo.paciente.DatosRegistroPaciente;
import med.voll.demo.paciente.Paciente;
import med.voll.demo.paciente.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@PostMapping
	public void registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente) {
		pacienteRepository.save(new Paciente(datosRegistroPaciente));
		
	}
	@GetMapping
	public Page<DatosListadoPaciente> ListadoPacientes(Pageable paginacion){
		return pacienteRepository.findAll(paginacion).map(DatosListadoPaciente::new);
	}
	
	@PutMapping
	@Transactional
	public void actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente datosActualizarPaciente) {
		Paciente paciente = pacienteRepository.getReferenceById(datosActualizarPaciente.id());
		paciente.actualizarDatos(datosActualizarPaciente);
	}
	
	//Delete De Base de Datos
		public void eliminarPaciente(@PathVariable Long id){
			Paciente paciente = pacienteRepository.getReferenceById(id);
			pacienteRepository.delete(paciente);
			
		}
}
