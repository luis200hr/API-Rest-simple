package med.voll.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import med.voll.demo.medico.DatosListadoMedico;
import med.voll.demo.medico.DatosRegistroMedico;
import med.voll.demo.medico.Medico;
import med.voll.demo.medico.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@PostMapping
	public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico) {
		medicoRepository.save(new Medico(datosRegistroMedico));
	}
	
	@GetMapping
	public Page<DatosListadoMedico> ListadoMedicos(@PageableDefault() Pageable paginacion){
		//return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
		return medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new);
	}
	
	@PutMapping
	@Transactional
	public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
		Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
		medico.actualizarDatos(datosActualizarMedico);
	}
	
	//Delete logico
	@DeleteMapping("/{id}")
	@Transactional
	public void eliminarMedico(@PathVariable Long id){
		Medico medico = medicoRepository.getReferenceById(id);
		medico.desactivarMedico();
		
	}
	
	//Delete De Base de Datos
	//public void eliminarMedico(@PathVariable Long id){
		//Medico medico = medicoRepository.getReferenceById(id);
	//	medicoRepository.delete(medico);
		
	//}
}
