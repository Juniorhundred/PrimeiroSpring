package br.com.primeiroprojetospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.primeiroprojetospring.domain.Professor;
import br.com.primeiroprojetospring.service.ProfessorService;

@Controller
@RequestMapping("professor")
public class ProfessorController {

	@Autowired
	private ProfessorService ProfessorService;

	@GetMapping("/listaProfessores")
	public ModelAndView listaTodosProfessores() {
		ModelAndView mView = new ModelAndView("professor/paginalistaProfessores");
		mView.addObject("professor", ProfessorService.buscarTodosProfessores());
		return mView;

	}
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarProfessores() {
		ModelAndView mView = new ModelAndView("professor/cadastraProfessor");
		mView.addObject("professor", new Professor());
		return mView;
	}

	@PostMapping("/salvar")
	public ModelAndView salvarProfessor(Professor professor) {
		ProfessorService.salvar(professor);
		return listaTodosProfessores();
	}
	@GetMapping("/alterar/{id}")
	public ModelAndView alterarProfessor (@PathVariable("id") Integer idProfessor) {
		ModelAndView mView = new ModelAndView("professor/alteraProfessor");
		mView.addObject("professor", ProfessorService.buscarPorID(idProfessor));
		return mView;
	}
	@PostMapping("/alterar")
	public ModelAndView alterar(Professor professorAlterado) {
		ProfessorService.salvarAlteracao(professorAlterado);
		return listaTodosProfessores();
	}
	@GetMapping("/excluir/{id}")
	public ModelAndView excluirProfessor (@PathVariable("id") Integer id) {
		ProfessorService.excluir(id);
		return listaTodosProfessores();
	}
}
