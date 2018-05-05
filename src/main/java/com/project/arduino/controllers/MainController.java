package com.project.arduino.controllers;

import com.project.arduino.services.LocksService;
import com.project.arduino.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.project.arduino.models.UsersEntity;
import com.project.arduino.models.LocksEntity;
import org.springframework.web.jsf.FacesContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.validation.Valid;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    UsersService userService;

    @Autowired
    LocksService locksService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getFirstPage(Model model){
        return "firstPage";
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getSongs(Model model){
        List<LocksEntity> locks;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsersEntity user = userService.FindByLogin(auth.getName());
        locks = locksService.findLocks(user);
        model.addAttribute("locks",locks);
        return "index";
    }

   @RequestMapping(value = "/addLock", method = RequestMethod.POST)
    public String saveLock(@ModelAttribute("locks") LocksEntity lock, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsersEntity user = userService.FindByLogin(auth.getName());
        lock.setUsersByUser(user);
        if(lock.getPassword()!=null) {
            locksService.saveLock(lock);
            LocksEntity neLock = new LocksEntity();
            model.addAttribute("locks",neLock);
        }
        return "addLock";
    }


    @RequestMapping(value = "/{id}/checkLock", method = RequestMethod.GET)
    public ModelAndView addLock(@PathVariable String id){
        LocksEntity lock = locksService.FindByTitle(id);
        LocksEntity newLock= new LocksEntity();
        newLock.setIdLock(lock.getIdLock());
        newLock.setUsersByUser(lock.getUsersByUser());
        newLock.setTitle(lock.getTitle());
        ModelAndView model = new ModelAndView();
        model.addObject("lock",newLock);
        model.setViewName("check");
        return model;
    }

    @RequestMapping(value = "/checkLock", method = RequestMethod.POST)
    public ModelAndView check(@ModelAttribute("lock") @Valid LocksEntity lock, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        LocksEntity lockCheck = locksService.FindByTitle(lock.getTitle());
        System.out.print("!"+lock.getPassword());
        if (!lockCheck.getPassword().equals(lock.getPassword())) {
            bindingResult
                    .rejectValue("password", "error.password",
                            "*Your password is incorrect");
            try(FileWriter writer = new FileWriter("/home/maria/IdeaProjects/Port/a.txt", false))
            {
                String text = "b";
                writer.write(text);
                writer.flush();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("check");}
        else {
            modelAndView.addObject("successMessage", "Lock has been opened");
            modelAndView.addObject("title", lock.getTitle());
            modelAndView.addObject("password", lock.getPassword());
            modelAndView.setViewName("check");
            try(FileWriter writer = new FileWriter("/home/maria/IdeaProjects/Port/a.txt", false))
            {
                String text = "a";
                writer.write(text);
                writer.flush();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/deleteLock", method = RequestMethod.GET)
    public ModelAndView deletePosted(@PathVariable int id){
        ModelAndView model = new ModelAndView();
        locksService.deleteLock(id);
       // model.addObject("lock",newLock);
        model.setViewName("delete");
        return model;
    }

}
