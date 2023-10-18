package com.aulateste.aulateste.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aulateste.aulateste.entities.Usuario;
import com.aulateste.aulateste.repositories.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	
	@Autowired //instanciar automaticamente - injeção de dependencia
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<Usuario> findAll(){
		List<Usuario> result = usuarioRepository.findAll();
		return result;
	}
	
	@GetMapping(value = "/{id}")
	public Usuario findById(@PathVariable Long id){
		Usuario result = usuarioRepository.findById(id).get();
		return result;
	}
	
	@PostMapping
	public Usuario insert(@RequestBody Usuario usuario){
		Usuario result = usuarioRepository.save(usuario);
		return result;
	}
	
	@PutMapping("/{id}")
	public Usuario update(@PathVariable Long id, @RequestBody Usuario novoUsuario) {
	    Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

	    if (usuarioOptional.isPresent()) {
	        Usuario usuario = usuarioOptional.get();
	        usuario.setNome(novoUsuario.getNome()); // Atualize os campos necessários
	        usuario.setEmail(novoUsuario.getEmail()); // Adicione mais campos conforme necessário
	        // Faça as atualizações necessárias no objeto usuário com os novos valores fornecidos

	        Usuario resultado = usuarioRepository.save(usuario);
	        return resultado;
	    } else {
	        // Lide com o caso em que o usuário com o ID fornecido não existe
	        // Você pode lançar uma exceção ou retornar uma resposta apropriada, como um status 404 (Not Found).
	        // Neste exemplo, retornaremos null para indicar que o usuário não foi encontrado.
	        return null;
	    }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> delete(@PathVariable Long id) {
	    Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

	    if (usuarioOptional.isPresent()) {
	        usuarioRepository.deleteById(id);
	        return ResponseEntity.ok().build();
	    } else {
	        // Lide com o caso em que o usuário com o ID fornecido não existe
	        // Você pode retornar uma resposta apropriada, como um status 404 (Not Found).
	        // Neste exemplo, retornaremos uma resposta 404 para indicar que o usuário não foi encontrado.
	        return ResponseEntity.notFound().build();
	    }
	}
}
