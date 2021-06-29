package br.org.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.org.generation.blogpessoal.model.Tema;
import br.org.generation.blogpessoal.repository.TemaRepository;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

	@Autowired
	private TemaRepository temaRepository;

	@GetMapping
	public ResponseEntity<List<Tema>> getAll() {
		return ResponseEntity.ok(temaRepository.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable long id) {
		return temaRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao) {
		return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	@PostMapping
	public ResponseEntity<Tema> postTema(@RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
	}

	/**
	 * Optional<Tema> temaUpdate = temaRepository.findById(tema.getId());: Cria um 
	 * objeto do tipo Tema (temaUpdate) que receberá o resultado 
	 * da busca do tema por id (lê o id do objeto tema a partir do método getId())
	 * 
	 * if (temaUpdate.isPresent()): Verifica se o tema foi encontrado (se o objeto 
	 * temaUpdate está preenchido)
	 * 
	 * return ResponseEntity.status(HttpStatus.OK).body(temaRepository.save(tema));: 
	 * Se o tema foi encontrado, o mesmo será atualizado através do objeto tema
	 * 
	 * throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema não encontrado!", null);: 
	 * Caso contrário, será exibido um HTTP Status Not Found = 404, ou seja, tema 
	 * não encontrado
	 */

	@PutMapping
	public ResponseEntity<Tema> putTema(@RequestBody Tema tema) {

		Optional<Tema> temaUpdate = temaRepository.findById(tema.getId());

		if (temaUpdate.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(temaRepository.save(tema));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema não encontrado!", null);
		}

	}

	@DeleteMapping("/{id}")
	public void deleteTema(@PathVariable long id) {

		/**
		 * Optional<Tema> tema = temaRepository.findById(id);: Cria um objeto 
		 * do tipo Tema que receberá o resultado da busca do tema por id 
		 * (variável de caminho)
		 * 
		 * if (tema.isPresent()): Verifica se o tema foi encontrado (se o objeto 
		 * tema está preenchido)
		 * 
		 * temaRepository.deleteById(id);: Se o tema foi encontrado, o mesmo será 
		 * apagado
		 * 
		 * throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema não encontrado!", null);:
		 * Caso contrário, será exibido um HTTP Status Not Found = 404, ou seja, tema 
		 * não encontrado
		 */

		Optional<Tema> tema = temaRepository.findById(id);

		if (tema.isPresent()) {
			temaRepository.deleteById(id);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema não encontrado!", null);
		}
	}

}
