package platform.service;


import javassist.NotFoundException;
import platform.dto.CodeDto;
import platform.model.Code;

import java.util.List;

public interface CodeServiceImpl {
    Code add(CodeDto code);
    void remove(Code code);
    List<Code> getAll();
    Code getById(String  id) throws NotFoundException;

    void save(Code code);
}
