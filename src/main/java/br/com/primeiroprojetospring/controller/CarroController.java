package br.com.primeiroprojetospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.primeiroprojetospring.domain.Carro;
import br.com.primeiroprojetospring.service.AcessorioService;
import br.com.primeiroprojetospring.service.CarroService;
import br.com.primeiroprojetospring.service.ChaveService;
import br.com.primeiroprojetospring.service.DocumentoService;
import br.com.primeiroprojetospring.service.FabricanteService;

@RestController
@RequestMapping("carro")
public class CarroController {
	
	@Autowired
	private CarroService carroService;
	
	@Autowired
	private ChaveService chaveService;
	
	@Autowired
	private DocumentoService documentoService;
	
	@Autowired
	private AcessorioService acessorioService;
	
	@Autowired
	private FabricanteService fabricanteService;
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Carro> find(@PathVariable("id") Integer id){
		return ResponseEntity.ok().body(carroService.buscarCarroID(id));
	}
	
	@PostMapping("/cadastrarCarro")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Carro> cadastrarCarroAPI(@RequestBody Carro carro) {
		return ResponseEntity.ok().body(carroService.salvar(carro));
	}
	
	
	@GetMapping("/todosCarros")
	public ResponseEntity<List<Carro>> devolveTodosCarros(){
		return ResponseEntity.ok().body(carroService.buscarTodosCarros());
	}
	
	@PutMapping("/alteraCarro")
	public ResponseEntity<Carro> alteraCarro(@RequestBody Carro carro){
		Carro novoCarro = carroService.salvarAlteracao(carro);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoCarro);
	}
	
	@GetMapping("/listaCarros")
	public ModelAndView  listaTodosCarro() {
		ModelAndView mView = new ModelAndView("carro/paginaListaCarros");
		mView.addObject("carro", carroService.buscarTodosCarros());
		return mView;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarCarro() {
		ModelAndView mView = new ModelAndView("carro/cadastrarCarro");
		mView.addObject("carro", new Carro());
		mView.addObject("chaves", chaveService.buscarTodasChaves());
		mView.addObject("documentos", documentoService.buscarTodosDocumentos());
		mView.addObject("acessorios", acessorioService.buscarTodosAcessorios());
		mView.addObject("fabricantes", fabricanteService.buscarTodosFabricantes());
		return mView;
	}
		
	@PostMapping("/salvar")
	public ModelAndView salvarCarro(Carro carro) {
		carroService.salvar(carro);
		return listaTodosCarro();
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alteraCarro(@PathVariable("id") Integer idCarro) {
		ModelAndView mView = new ModelAndView("carro/alteraCarro");
		mView.addObject("carro", carroService.buscarCarroID(idCarro));	
		mView.addObject("chaves", chaveService.buscarTodasChaves());
		mView.addObject("documentos", documentoService.buscarTodosDocumentos());
		mView.addObject("acessorios", acessorioService.buscarTodosAcessorios());
		mView.addObject("fabricantes", fabricanteService.buscarTodosFabricantes());
		return mView;
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterar(Carro carroAlterado) {
		carroService.salvarAlteracao(carroAlterado);
		return listaTodosCarro();
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Integer id) {
		carroService.excluir(id);
		return listaTodosCarro();
	}

}