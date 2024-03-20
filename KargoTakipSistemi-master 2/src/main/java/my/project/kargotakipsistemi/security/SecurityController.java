package my.project.kargotakipsistemi.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import my.project.kargotakipsistemi.dto.saveReqDtos.CustomerSaveReqDto;
import my.project.kargotakipsistemi.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SecurityController {

    private final CustomerService customerService;

    public SecurityController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("customerSaveReqDto", new CustomerSaveReqDto());
        return "login.html";
    }

    @PostMapping("/signup")
    public String loginPost(@ModelAttribute("customerSaveReqDto") CustomerSaveReqDto customerSaveReqDto, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        //Write validations for customerSaveReqDto
        //If validations fail, return login.html with error messages
        //If validations succeed, save customer to database and redirect to login.html with success message
        //If customer already exists, return login.html with error message

        if (customerSaveReqDto.getName() == null || customerSaveReqDto.getName().isBlank() || customerSaveReqDto.getName().isEmpty()) {
            redirectAttributes.addFlashAttribute("error_name", "Name cannot be empty");
            return "redirect:/login";
        }
        if (customerSaveReqDto.getPassword() == null || customerSaveReqDto.getPassword().isBlank() || customerSaveReqDto.getPassword().isEmpty() || customerSaveReqDto.getPasswordConfirm() == null || customerSaveReqDto.getPasswordConfirm().isBlank() || customerSaveReqDto.getPasswordConfirm().isEmpty() || !customerSaveReqDto.getPassword().equals(customerSaveReqDto.getPasswordConfirm())) {
            redirectAttributes.addFlashAttribute("error_pass", "Password cannot be empty and Passwords must match");
            return "redirect:/login";
        }
        if (customerSaveReqDto.getEmail() == null || customerSaveReqDto.getEmail().isBlank() || customerSaveReqDto.getEmail().isEmpty()) {
            redirectAttributes.addFlashAttribute("error_mail", "Email cannot be empty");
            return "redirect:/login";
        }
        if (customerSaveReqDto.getPhoneNumber() == null || customerSaveReqDto.getPhoneNumber().isBlank() || customerSaveReqDto.getPhoneNumber().isEmpty()) {
            redirectAttributes.addFlashAttribute("error_num", "Phone number cannot be empty");
            return "redirect:/login";
        }

        customerService.saveCustomer(customerSaveReqDto);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Başarılı kayıt mesajını ekle
        redirectAttributes.addFlashAttribute("success", "You have registered successfully. Please log in.");


        return "redirect:/login";
    }

}
