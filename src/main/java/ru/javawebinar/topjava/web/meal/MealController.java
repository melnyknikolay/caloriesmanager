package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by nik_PC on 28.01.2018.
 */

@Controller
public class MealController extends AbstractMealController {

    @GetMapping("/meals/{id}")
    public String getMeal(Model model, @PathVariable int id){
        model.addAttribute("meal", super.get(id));
        return "mealForm";
    }

    @PostMapping("/meals/{id}")
    public String setMeal(Model model, @PathVariable int id){
        model.addAttribute("meal", super.get(id));
        return "mealForm";
    }
}
