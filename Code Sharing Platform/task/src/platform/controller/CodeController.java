package platform.controller;

import com.fasterxml.jackson.annotation.JsonView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.model.Code;
import platform.repo.CodeRepository;
import platform.service.CodeService;
import platform.service.CodeServiceImpl;
import platform.views.Views;

import java.util.List;

@Controller
public class CodeController {
    @Autowired
    private CodeService codeService;


    @GetMapping("/code/new")
    public String getCode(Model model) {
        model.addAttribute("title", "Create");
        return "create";
    }

    @JsonView(Views.FullWithoutId.class)
    @GetMapping("/code/{id}")
    public String getCodeById(@PathVariable("id") String id, Model model) throws NotFoundException {
        model.addAttribute("title", "Code");
        Code code = codeService.getById(id);
        System.out.println(code.getTime() + " " + code.getViews());
        model.addAttribute("code", code);
        return "code";
    }
    @JsonView(Views.FullWithoutId.class)
    @GetMapping("/code/latest")
    public String getLatest(Model model) {
        model.addAttribute("title", "Latest");
        List<Code> codesTenOrLow = codeService.getLatest();
        model.addAttribute("latestCode", codesTenOrLow);
        return "latest";
    }

}
