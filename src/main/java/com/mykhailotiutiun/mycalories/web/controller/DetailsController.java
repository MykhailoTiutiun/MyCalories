package com.mykhailotiutiun.mycalories.web.controller;

import com.mykhailotiutiun.mycalories.persistence.dto.DetailsDto;
import com.mykhailotiutiun.mycalories.persistence.dto.DietDto;
import com.mykhailotiutiun.mycalories.persistence.dto.DtoConverter;
import com.mykhailotiutiun.mycalories.persistence.models.Details;
import com.mykhailotiutiun.mycalories.persistence.models.Diet;
import com.mykhailotiutiun.mycalories.persistence.models.User;
import com.mykhailotiutiun.mycalories.services.DetailsService;
import com.mykhailotiutiun.mycalories.services.DietService;
import com.mykhailotiutiun.mycalories.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/details")
public class DetailsController {

    private final DetailsService detailsService;
    private final UserService userService;
    private final DietService dietService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public DetailsController(DetailsService detailsService, UserService userService, DietService dietService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.detailsService = detailsService;
        this.userService = userService;
        this.dietService = dietService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/")
    public String detailsPage(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userService.getUserById(user.getId());

        model.addAttribute("user", DtoConverter.userDtoFromUser(user));
        model.addAttribute("details", DtoConverter.detailsDtoFromDetails(user.getDetails()));
        model.addAttribute("diet", DtoConverter.dietDtoFromDiet(user.getDiet()));

        return "profile-details";
    }

    @PostMapping("/update-main")
    public String updateMain(@ModelAttribute @Valid DetailsDto detailsDto, BindingResult result) {
        if(result.hasErrors()) {
            return "redirect:/details/?mainError=True";
        }

            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            user = userService.getUserById(user.getId());

            Details details = DtoConverter.detailsFromDetailsDto(detailsDto);
            detailsService.setDetailsParams(user.getId(), details.getAge(), details.getHeight(), details.getWeight(), details.getFat());
        return "redirect:/details/";
    }

    @PostMapping("/update-diet")
    public String updateDiet(@ModelAttribute DietDto dietDto, @ModelAttribute(name = "action") String action, BindingResult result, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userService.getUserById(user.getId());

        if(result.hasErrors()) {
            return "redirect:/details/?dietError=True";
        } else if(action.equals("Update")) {
            Diet diet = DtoConverter.dietFromDietDto(dietDto);
            dietService.setDailyParams(user.getId(), diet.getDailyCalories(), diet.getDailyProteins(), diet.getDailyCarbs(), diet.getDailyFats());
        } else if(action.equals("Calculate")){
            float bodyweight = user.getDetails().getWeight().floatValue() - user.getDetails().getWeight().floatValue() * user.getDetails().getFat().floatValue() / 100;
            dietService.setDailyParams(user.getId(), bodyweight * 33, bodyweight * 2, bodyweight * 4, bodyweight * 0.8F);
        } else {
            throw new IndexOutOfBoundsException("Variable 'action' (" + action + ") is out of bounds");
        }
        return "redirect:/details/";
    }

    @PostMapping("/change-password")
    public String changePassword(@ModelAttribute(name = "old-password") String oldPassword, @ModelAttribute(name = "password") String password, @ModelAttribute(name = "password-conf") String passwordConf){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userService.getUserById(user.getId());

        if(!bCryptPasswordEncoder.matches(oldPassword, user.getPassword())){
            return "redirect:/details/?oldPasswordError=True";
        } else if(!password.equals(passwordConf)){
            return "redirect:/details/?passwordMatchError=True";
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(password));
            userService.saveUser(user);
        }

        return "redirect:/";
    }
}
