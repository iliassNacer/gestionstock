package com.gestionstock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }

    @GetMapping("/rapports")
    public String rapports(Model model) {
        return "rapports";
    }

    @GetMapping("/parametres")
    public String parametres(Model model) {
        return "parametres";
    }
} 