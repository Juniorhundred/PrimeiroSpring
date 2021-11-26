package br.com.primeiroprojetospring.inicializacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.primeiroprojetospring.domain.Aluno;
import br.com.primeiroprojetospring.domain.Professor;
import br.com.primeiroprojetospring.service.AlunoService;
import br.com.primeiroprojetospring.service.ProfessorService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private AlunoService AlunoService;
	@Autowired
	private ProfessorService ProfessorService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		Aluno aluno1 = new Aluno();
		aluno1.setNome("Fulano1");
		AlunoService.salvar(aluno1);

		Aluno aluno2 = new Aluno();
		aluno2.setNome("Roberto1");
		AlunoService.salvar(aluno2);

		Aluno aluno3 = new Aluno();
		aluno3.setNome("Carlos1");
		AlunoService.salvar(aluno3);

		List<Aluno> listaAluno = AlunoService.buscarTodosAlunos();

		for (Aluno aluno : listaAluno) {
			System.out.println(aluno.getNome());
		}

		Professor Professor1 = new Professor();
		Professor1.setNome("Sandro");
		ProfessorService.salvar(Professor1);

		Professor Professor2 = new Professor();
		Professor2.setNome("Antonio");
		ProfessorService.salvar(Professor2);

		Professor Professor3 = new Professor();
		Professor3.setNome("Junior");
		ProfessorService.salvar(Professor3);

		List<Professor> listaProfessor = ProfessorService.buscarTodosProfessores();

		for (Professor professor : listaProfessor) {
			System.out.println(professor.getNome());
		}
	}
}
