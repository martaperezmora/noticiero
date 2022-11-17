package com.noticiero.examen.noticiero.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.noticiero.examen.noticiero.model.Noticia;
import com.noticiero.examen.noticiero.model.Usuario;

@Controller
@RequestMapping("/noticiero")
public class noticieroController {
    
    @RequestMapping(value= {"/noticias"})
    public ModelAndView lista(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticias", getNoticias());
        modelAndView.setViewName("noticiero/noticias");

        return modelAndView;
    }

    @PostMapping(path = {"/login"})
    public ModelAndView login(Usuario usuario, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        
        session.setAttribute("usuario", usuario);
        
        modelAndView.setViewName("noticiero/noticias");

        return modelAndView;
    }

    @RequestMapping(value= {"/nuevo"})
    public ModelAndView nuevo(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("noticiero/nuevo");

        return modelAndView;
    }

    @PostMapping(path = {"/guardar"})
    public ModelAndView guardar(Noticia noticia){
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.addObject("noticias", addNoticia(noticia));
        modelAndView.setViewName("noticiero/noticias");

        return modelAndView;
    }

    @GetMapping(path = {"/editar/{codigo}"})
    public ModelAndView editar(@PathVariable(name="codigo", required=true) int codigo){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticia", getNoticia(codigo));
        modelAndView.setViewName("noticiero/editar");

        return modelAndView;

    }

    @PostMapping(path = {"/modificar"})
    public ModelAndView modificar(Noticia noticia){
        ModelAndView modelAndView = new ModelAndView();

        List<Noticia> noticias = getNoticias();
        int indexOf = noticias.indexOf(noticia);
        noticias.set(indexOf, noticia);

        modelAndView.addObject("noticias", noticias);
        modelAndView.setViewName("noticiero/noticias");

        return modelAndView;
    }

    @GetMapping(path = {"/borrar/{codigo}"})
    public ModelAndView borrar(@PathVariable(name="codigo", required=true) int codigo){
        ModelAndView modelAndView = new ModelAndView();
        List<Noticia> noticias = getNoticias();
        int indexOf = noticias.indexOf(new Noticia(codigo));
        noticias.remove(indexOf);
        modelAndView.addObject("noticias", noticias);
        modelAndView.setViewName("noticiero/noticias");
        
        return modelAndView;
    }


    //funciones
    // - - - - -
    private List<Noticia> addNoticia(Noticia noticia){
        List<Noticia> noticias = getNoticias();
        noticias.add(noticia);
        return noticias;
    }

    private Noticia getNoticia(int codigo){
        List<Noticia> noticias = getNoticias();
        int indexOf = noticias.indexOf(new Noticia(codigo));
        
        return noticias.get(indexOf);
    }

    private List<Noticia> getNoticias(){
        List<Noticia> noticias = new ArrayList<Noticia>();
        noticias.add(new Noticia(1,"Titular 1", "Descripcion 1"));
        noticias.add(new Noticia(2,"Titular 2", "Descripcion 2"));
        noticias.add(new Noticia(3,"Titular 3", "Descripcion 3"));
        noticias.add(new Noticia(4,"Titular 4", "Descripcion 4"));

        return noticias;
    }
}
