package br.com.primeiroprojetospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.primeiroprojetospring.domain.Aluno;
import br.com.primeiroprojetospring.service.AlunoService;

@Controller
@RestController
@RequestMapping("aluno")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@GetMapping("/find/{id}")
	public ResponseEntity<Aluno> find(@PathVariable("id") Integer id){
		return ResponseEntity.ok().body(alunoService.buscarPorID(id));
	}
	
	@PostMapping("/cadastrarAluno")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Aluno> cadastrarAlunoAPI(@RequestBody Aluno aluno) {
		return ResponseEntity.ok().body(alunoService.salvar(aluno));
	}
	
	
	@GetMapping("/todosAlunos")
	public ResponseEntity<List<Aluno>> devolveTodosAlunos(){
		return ResponseEntity.ok().body(alunoService.buscarTodosAlunos());
	}
	
	@PutMapping("/alteraAluno")
	public ResponseEntity<Aluno> alteraAluno(@RequestBody Aluno aluno){
		Aluno novoAluno = alunoService.salvarAlteracao(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
	}
	
	
	@GetMapping("/listaAlunos")
	public ModelAndView listaTodosAlunos() {
		ModelAndView mView = new ModelAndView("aluno/paginaListaAlunos");
		mView.addObject("alunos", alunoService.buscarTodosAlunos());
		return mView;

	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrarAlunos() {
		ModelAndView mView = new ModelAndView("aluno/cadastraAluno");
		mView.addObject("aluno", new Aluno());
		return mView;
	}

	@PostMapping("/salvar")
	public ModelAndView salvarAlunos(Aluno aluno) {
		alunoService.salvar(aluno);
		return listaTodosAlunos();

	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterarAluno(@PathVariable("id") Integer idAluno) {
		ModelAndView mView = new ModelAndView("aluno/alteraAluno");
		mView.addObject("aluno", alunoService.buscarPorID(idAluno));
		return mView;
	}

	@PostMapping("/alterar")
	public ModelAndView alterar(Aluno alunoAlterado) {
		alunoService.salvarAlteracao(alunoAlterado);
		return listaTodosAlunos();
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluirAluno(@PathVariable("id") Integer id) {
		alunoService.excluir(id);
		return listaTodosAlunos();
	}
}
