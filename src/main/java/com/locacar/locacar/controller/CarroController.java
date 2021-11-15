package com.locacar.locacar.controller;

import com.locacar.locacar.entity.Carro;
import com.locacar.locacar.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class CarroController {

    @Autowired
    private CarroService service;

    @Autowired
    public void setService(CarroService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String getHome(Model model) {
        List<Carro> carroList = service.findAll();
        model.addAttribute(carroList);
        return "home.html";
    }

    @PostMapping("**/pesquisarveiculo")
    public ModelAndView pesquisar(@RequestParam("pesquisaVeiculo") String pesquisaVeiculo) {
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("carroList", service.findByVeiculo(pesquisaVeiculo));
        modelAndView.addObject("veiculoobj", new Carro());
        return modelAndView;
    }


    @RequestMapping("/cadastrar")
    public String getCadastrar(Model model) {
        Carro c = new Carro();
        model.addAttribute("novoCarro", c);
        return "cadastrar";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String doSalvar(@ModelAttribute(name = "novoCarro") @Valid Carro carro, Errors errors, RedirectAttributes r) {
        if (errors.hasErrors()) {
            return "cadastrar";
        } else {
            r.addFlashAttribute("sucesso", true);
            service.save(carro);
            ;
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/atualizar", method = RequestMethod.POST)
    public String doAtualizar(@ModelAttribute(name = "carro") @Valid Carro carro, Errors errors, RedirectAttributes r) {
        if (errors.hasErrors()) {
            return "editar";
        } else {
            r.addFlashAttribute("atualizou", true);
            service.save(carro);
            ;
            return "redirect:/";
        }
    }

    @RequestMapping("/editar/{id}")
    public ModelAndView getEditar(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("editar");
        Carro c = service.getById(id);
        modelAndView.addObject("carro", c);
        return modelAndView;
    }

    @RequestMapping("/deletar/{id}")
    public String doDelete(@PathVariable(name = "id") Long id, RedirectAttributes r) {
        r.addFlashAttribute("apagou", true);
        service.delete(id);
        return "redirect:/";
    }


    @PostMapping("**/pesquisardecadaveiculo")
    public ModelAndView pesquisarDecadaVeiculo(@RequestParam("pesquisadecadaveiculo") String pesquisadecadaveiculo) {
        ModelAndView modelAndView = new ModelAndView("/home");
        if (StringUtils.hasText(pesquisadecadaveiculo)) {
            modelAndView.addObject("carroList", service.findByDecada(Integer.valueOf(pesquisadecadaveiculo)));
            modelAndView.addObject("decadaobj", new Carro());
        } else {
            modelAndView.addObject("carroList", service.findAll());
        }
        return modelAndView;
    }

    @PostMapping("**/pesquisarmarcaveiculo")
    public ModelAndView pesquisarMarca(@RequestParam("pesquisamarcaveiculo") String pesquisamarcaveiculo) {
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("carroList", service.findByMarca(pesquisamarcaveiculo));
        modelAndView.addObject("marcaobj", new Carro());
        return modelAndView;
    }

    @PostMapping("**/pesquisarvendidoveiculo")
    public ModelAndView pesquisarVendido(@RequestParam(value = "pesquisavendidoveiculo") String pesquisavendidoveiculo) {
        ModelAndView modelAndView = new ModelAndView("/home");

        if (StringUtils.hasText(pesquisavendidoveiculo)) {
            modelAndView.addObject("carroList", service.findByVendido(Boolean.valueOf(pesquisavendidoveiculo)));
            modelAndView.addObject("vendidoobj", new Carro());

        } else {
            modelAndView.addObject("carroList", service.findAll());
        }
        return modelAndView;
    }

    @PostMapping("**/filterdates")
    public ModelAndView filtrarPordataAsc(@RequestParam("filterdate") String filterdate) {
        ModelAndView modelAndView = new ModelAndView("/home");
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        if (StringUtils.hasText(filterdate) && filterdate.equals("10/11/2021")) {
            try {
                modelAndView.addObject("carroList", service.findBydataasc(formatador.parse(filterdate)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            modelAndView.addObject("vendidoobj", new Carro());
        } else if (StringUtils.hasText(filterdate) && filterdate.equals("11/11/2021")) {
            try {
                modelAndView.addObject("carroList", service.findBydatadesc(formatador.parse(filterdate)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            modelAndView.addObject("vendidoobj", new Carro());
        } else {
            modelAndView.addObject("carroList", service.findAll());
        }
        return modelAndView;
    }

}
