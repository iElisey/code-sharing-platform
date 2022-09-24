package platform.controller;

import com.fasterxml.jackson.annotation.JsonView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.dto.CodeDto;
import platform.dto.ResponseDto;
import platform.model.Code;
import platform.service.CodeService;
import platform.views.Views;

import java.util.*;

@RestController
public class CodeApiController {
    @Autowired
    private CodeService codeService;

    @JsonView(Views.Id.class)
    @PostMapping("/api/code/new")
    public ResponseEntity<?> createCode(@RequestBody CodeDto codeDto) {
        if (codeDto != null) {
            Map<Object, Object> map = new HashMap<>();
            Code codeNew = codeService.add(codeDto);
            map.put("id", codeNew.getId());
            return ResponseEntity.ok(map);
        }
        return ResponseEntity.badRequest().body(new ResponseDto("The code must not be empty!"));
    }

    @JsonView(Views.FullWithoutId.class)
    @GetMapping("/api/code/{id}")
    public ResponseEntity<?> getCode(@PathVariable("id") String id)  {
        Code code = codeService.getById(id);
        return ResponseEntity.ok(code);
    }

    @JsonView(Views.FullWithoutId.class)
    @GetMapping("/api/code/latest")
    public ResponseEntity<?> getLatest() {
        List<Code> tenOrLowCodes = codeService.getLatest();
        return ResponseEntity.ok(tenOrLowCodes);
    }


}
