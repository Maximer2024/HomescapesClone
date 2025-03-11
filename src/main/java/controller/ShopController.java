package com.mygame.homescapesclone.controller;

import com.mygame.homescapesclone.model.Item;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ShopController {

    private final List<Item> items = Arrays.asList(
            new Item(1, "🍀", "Зелье энергии", 20),
            new Item(2, "⏳", "Ускоритель времени", 50),
            new Item(3, "❤️", "Любовь Максима", 300)
    );

    @GetMapping("/shop")
    public String shop(Model model, HttpSession session) {
        Integer balance = (Integer) session.getAttribute("balance");
        if (balance == null) {
            balance = 100;
            session.setAttribute("balance", balance);
        }

        model.addAttribute("balance", balance);
        model.addAttribute("items", items);
        return "shop";
    }

    @PostMapping("/buy")
    @ResponseBody
    public Map<String, String> buyItem(@RequestParam int itemId, HttpSession session) {
        Map<String, String> response = new HashMap<>();
        Integer balance = (Integer) session.getAttribute("balance");

        if (balance == null) {
            balance = 100;
        }

        Optional<Item> itemOpt = items.stream().filter(i -> i.getId() == itemId).findFirst();

        if (itemOpt.isPresent()) {
            Item item = itemOpt.get();
            System.out.println("Покупка товара: " + item.getName() + " за " + item.getPrice());

            if (balance >= item.getPrice()) {
                balance -= item.getPrice();
                session.setAttribute("balance", balance);
                response.put("success", "Покупка совершена успешно!");
                System.out.println("Баланс после покупки: " + balance);

                if (item.getId() == 3) {
                    response.put("special", "💖 Ты самая красивая девушка в мире, и я тебя люблю!");
                    System.out.println("Активирован special-контент!");
                }
            } else {
                response.put("error", "Нет, зайка, денег не достаточно!");
                System.out.println("Ошибка: Недостаточно средств.");
            }
        } else {
            response.put("error", "Ошибка! Такого товара нет.");
            System.out.println("Ошибка: Товар не найден.");
        }

        return response;
    }
}
