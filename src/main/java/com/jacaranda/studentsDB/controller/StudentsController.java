package com.jacaranda.studentsDB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jacaranda.studentsDB.model.Student;
import com.jacaranda.studentsDB.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class StudentsController {
	// Solo se define el objeto, el lo creara cuando sea necesario
	@Autowired
	StudentService repositorio;

	@GetMapping("listStudent")
	// Recibe un modelo
	public String listStudent(Model model) {
		// Le añadimos un atributo, ahora puede usarlo en
		// su plantilla o vista
		model.addAttribute("lista", repositorio.getStudents());
		return "listStudent";
	}

	@GetMapping("addStudent")
	public String addStudent(Model model) {
		// Se crea el objeto
		Student student = new Student();

		// Se le pasa al modelo para que la plantilla lo utilice cuando le haga falta
		model.addAttribute("estudiante", student);
		return "addStudent";
	}

	@PostMapping("execAdd")
	// Recibe un estudiante que le ha mandado una vista
	public String execAdd(@Validated @ModelAttribute("estudiante") Student pepe, BindingResult errors) {
		if (errors.hasErrors()) {
			return "addStudent";
		} else {
			// Añade ese estudiante
			repositorio.add(pepe);
			// Redirige de nuevo al de la lista
			return "redirect:/listStudent";
		}
	}

	@GetMapping("delStudent")
	// Le paso que se requieren esos dos atributos cuando me llamen
	public String delStudent(Model model, @RequestParam(name = "id") long id) {
		// Creo el student que voy a mostrar en la plantilla
		// con esos parametros
		Student student = repositorio.getStudent(id);

		// Setteo a un atributo que se llama estudiante el student
		// Para que pueda usarse en la plantilla
		model.addAttribute("estudiante", student);
		return "borrarStudent";
	}

	@PostMapping("execDel")
	public String execdel(@ModelAttribute("estudiante") Student pepe) {
		repositorio.delete(pepe);
		return "redirect:/listStudent";
	}

	@GetMapping("updateStudent")
	public String updateStudent(Model model, @RequestParam(name = "id") long id) {
		Student estudiante = repositorio.getStudent(id);
		model.addAttribute("estudiante", estudiante);

		return "updateStudent";
	}

	@PostMapping("execUpdate")
	public String execUpdate(Model model, Student updateado) {
		repositorio.update(updateado);
		return "redirect:/listStudent";
	}

	@GetMapping("login")
	public String login() {
		return "login";
	}
}
