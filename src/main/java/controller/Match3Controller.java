package com.mygame.homescapesclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Match3Controller {

    @GetMapping("/game")
    public String game() {
        return "match3";
    }
}
