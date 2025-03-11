package com.mygame.homescapesclone.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CoinController {

    @GetMapping("/get-balance")
    public Map<String, Integer> getBalance(HttpSession session) {
        Integer balance = (Integer) session.getAttribute("balance");
        if (balance == null) {
            balance = 100;
            session.setAttribute("balance", balance);
        }

        Map<String, Integer> response = new HashMap<>();
        response.put("balance", balance);
        return response;
    }

    @PostMapping("/add-coins")
    public Map<String, Integer> addCoins(@RequestBody Map<String, Integer> request, HttpSession session) {
        Integer balance = (Integer) session.getAttribute("balance");
        if (balance == null) {
            balance = 100;
        }

        int amount = request.get("amount");
        balance += amount;
        session.setAttribute("balance", balance);

        Map<String, Integer> response = new HashMap<>();
        response.put("new_balance", balance);
        return response;
    }
}
