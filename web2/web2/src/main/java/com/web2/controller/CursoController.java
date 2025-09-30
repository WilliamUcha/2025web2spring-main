package com.web2.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web2.dto.CursoDTO;
import com.web2.model.Categoria;
import com.web2.model.Curso;
import com.web2.model.Professor;
import com.web2.repository.CategoriaRepository;
import com.web2.repository.CursoRepository;
import com.web2.repository.ProfessorRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	CursoRepository repository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProfessorRepository professorRepository;
	
	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView mv = new ModelAndView("curso/inserir");
		List<Categoria> categorias = categoriaRepository.findAll();
		List<Professor> professores = professorRepository.findAll();
		mv.addObject("categorias", categorias);
		mv.addObject("professores", professores);
		return mv;
	}
	
	@PostMapping("/inserir")
	public String inserido(
			@ModelAttribute @Valid CursoDTO dto, 
			BindingResult result, 
			RedirectAttributes msg,
			@RequestParam("file") MultipartFile imagem) {
		if(result.hasErrors()) {
			msg.addFlashAttribute("erro", "Erro ao inserir curso!");
			return "redirect:/curso/listar";
		}
		
		Optional<Categoria> categoria = categoriaRepository.findById(dto.categoriaId());
		Optional<Professor> professor = professorRepository.findById(dto.professorId());
		
		if(categoria.isEmpty() || professor.isEmpty()) {
			msg.addFlashAttribute("erro", "Categoria ou Professor não encontrados!");
			return "redirect:/curso/listar";
		}
		
		var curso = new Curso();
		curso.setNome(dto.nome());
		curso.setDescricao(dto.descricao());
		curso.setDescricaoCompleta(dto.descricaoCompleta());
		curso.setCargaHoraria(dto.cargaHoraria());
		curso.setPreco(dto.preco());
		curso.setCategoria(categoria.get());
		curso.setProfessor(professor.get());
		
		try {
			if(!imagem.isEmpty()) {
				byte[] bytes = imagem.getBytes();
				
				Path caminho = Paths.get(
						"./src/main/resources/static/img/"+
								imagem.getOriginalFilename());
				
				Files.write(caminho, bytes);
				curso.setImagem(imagem.getOriginalFilename());
			}
		}catch(IOException e) {
			System.out.println("erro imagem");
		}
		
		repository.save(curso);
		msg.addFlashAttribute("sucesso", "Curso inserido!");
		return "redirect:/curso/listar";
	}
	
	@GetMapping("/imagem/{imagem}")
	@ResponseBody
	public byte[] mostraImagem(@PathVariable("imagem") String imagem) 
			throws IOException {
		File nomeArquivo = 
				new File("./src/main/resources/static/img/"+imagem);
		if(imagem != null && imagem.trim().length()>0) {
			return Files.readAllBytes(nomeArquivo.toPath());
		}
		return null;
	}
	
	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("curso/listar");
		List<Curso> lista = repository.findAll();
		mv.addObject("cursos", lista);
		return mv;
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable(value="id") int id, RedirectAttributes msg) {
		Optional<Curso> curso = repository.findById(id);
		if(curso.isEmpty()) {
			msg.addFlashAttribute("erro", "Curso não encontrado!");
			return "redirect:/curso/listar";			
		}
		repository.deleteById(id);
		msg.addFlashAttribute("sucesso", "Curso excluído!");
		return "redirect:/curso/listar";					
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable(value="id") int id) {
		ModelAndView mv = new ModelAndView("curso/editar");
		Optional<Curso> curso = repository.findById(id);
		if(curso.isPresent()) {
			List<Categoria> categorias = categoriaRepository.findAll();
			List<Professor> professores = professorRepository.findAll();
			
			mv.addObject("curso", curso.get());
			mv.addObject("categorias", categorias);
			mv.addObject("professores", professores);
		}
		return mv;
	}
	
	@PostMapping("/editar/{id}")
	public String editado(
			@ModelAttribute @Valid CursoDTO dto, 
			BindingResult result, 
			RedirectAttributes msg,
			@PathVariable(value="id") int id,
			@RequestParam(value = "file", required = false) MultipartFile imagem) {
		if(result.hasErrors()) {
			msg.addFlashAttribute("erro", "Erro ao editar curso!");
			return "redirect:/curso/listar";
		}
		
		Optional<Curso> cursoOpt = repository.findById(id);
		Optional<Categoria> categoria = categoriaRepository.findById(dto.categoriaId());
		Optional<Professor> professor = professorRepository.findById(dto.professorId());
		
		if(cursoOpt.isEmpty() || categoria.isEmpty() || professor.isEmpty()) {
			msg.addFlashAttribute("erro", "Curso, Categoria ou Professor não encontrados!");
			return "redirect:/curso/listar";
		}
		
		var curso = cursoOpt.get();
		curso.setNome(dto.nome());
		curso.setDescricao(dto.descricao());
		curso.setDescricaoCompleta(dto.descricaoCompleta());
		curso.setCargaHoraria(dto.cargaHoraria());
		curso.setPreco(dto.preco());
		curso.setCategoria(categoria.get());
		curso.setProfessor(professor.get());
		
		try {
			if(imagem != null && !imagem.isEmpty()) {
				byte[] bytes = imagem.getBytes();
				
				Path caminho = Paths.get(
						"./src/main/resources/static/img/"+
								imagem.getOriginalFilename());
				
				Files.write(caminho, bytes);
				curso.setImagem(imagem.getOriginalFilename());
			}
		}catch(IOException e) {
			System.out.println("erro imagem");
		}
		
		repository.save(curso);
		msg.addFlashAttribute("sucesso", "Curso editado!");
		return "redirect:/curso/listar";
	}
}
