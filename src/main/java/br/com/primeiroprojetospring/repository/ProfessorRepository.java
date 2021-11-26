package br.com.primeiroprojetospring.repository;

 


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import br.com.primeiroprojetospring.domain.Professor;




@Repository

public interface ProfessorRepository extends JpaRepository<Professor, Integer>{

}
