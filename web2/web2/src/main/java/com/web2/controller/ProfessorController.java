package com.web2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web2.dto.ProfessorDTO;
import com.web2.model.Professor;
import com.web2.repository.ProfessorRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
	
	@Autowired
	ProfessorRepository repository;
	
	@GetMapping("/inserir")
	public String inserir() {
		return "professor/inserir";
	}
	
	@PostMapping("/inserir")
	public String inserido(
			@ModelAttribute @Valid ProfessorDTO dto, 
			BindingResult result, 
			RedirectAttributes msg) {
		if(result.hasErrors()) {
			msg.addFlashAttribute("erro", "Erro ao inserir professor!");
			return "redirect:/professor/listar";
		}
		var professor = new Professor();		
		BeanUtils.copyProperties(dto, professor);
		repository.save(professor);
		msg.addFlashAttribute("sucesso", "Professor inserido!");
		return "redirect:/professor/listar";
	}
	
	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("professor/listar");
		List<Professor> lista = repository.findAll();
		mv.addObject("professores", lista);
		return mv;
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable(value="id") int id, RedirectAttributes msg) {
		Optional<Professor> professor = repository.findById(id);
		if(professor.isEmpty()) {
			msg.addFlashAttribute("erro", "Professor não encontrado!");
			return "redirect:/professor/listar";			
		}
		repository.deleteById(id);
		msg.addFlashAttribute("sucesso", "Professor excluído!");
		return "redirect:/professor/listar";					
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable(value="id") int id) {
		ModelAndView mv = new ModelAndView("professor/editar");
		Optional<Professor> professor = repository.findById(id);
		if(professor.isPresent()) {
			mv.addObject("id", professor.get().getId());
			mv.addObject("nome", professor.get().getNome());
			mv.addObject("email", professor.get().getEmail());
			mv.addObject("telefone", professor.get().getTelefone());
			mv.addObject("especializacao", professor.get().getEspecializacao());
			mv.addObject("curriculo", professor.get().getCurriculo());
		}
		return mv;
	}
	
	@PostMapping("/editar/{id}")
	public String editado(
			@ModelAttribute @Valid ProfessorDTO dto, 
			BindingResult result, 
			RedirectAttributes msg,
			@PathVariable(value="id") int id) {
		if(result.hasErrors()) {
			msg.addFlashAttribute("erro", "Erro ao editar professor!");
			return "redirect:/professor/listar";
		}
		Optional<Professor> professor = repository.findById(id);
		if(professor.isPresent()) {
			var professor2 = professor.get();
			BeanUtils.copyProperties(dto, professor2);
			repository.save(professor2);
			msg.addFlashAttribute("sucesso", "Professor editado!");
		}
		return "redirect:/professor/listar";
	}
}
