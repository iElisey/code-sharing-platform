package platform.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.dto.CodeDto;
import platform.exception.MyResourceNotFoundException;
import platform.model.Code;
import platform.repo.CodeRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CodeService implements CodeServiceImpl {
    @Autowired
    private CodeRepository codeRepository;


    @Override
    public Code add(CodeDto code) {
        Code newCode = new Code(code.getCode(), code.getTime() == null ? 0L : code.getTime(), code.getViews() == null ? 0L : code.getViews());

        codeRepository.save(newCode);
        return newCode;
    }

    @Override
    public void save(Code code) {
        codeRepository.save(code);
    }


    @Override
    public void remove(Code code) {
        codeRepository.delete(code);
    }

    @Override
    public List<Code> getAll() {
        return (List<Code>) codeRepository.findAll();
    }


    @Override
    public Code getById(String id) {
        Code code = codeRepository.findById(UUID.fromString(id)).orElse(null);
        if (code != null) {
            if (code.getViews() == 1) {
                code.setViews(code.getViews() - 1);
                codeRepository.delete(code);
            } else if (code.getViews() > 1) {
                code.setViews(code.getViews() - 1);
                codeRepository.save(code);
            }
        } else {
            throw new MyResourceNotFoundException("Not found!");
        }
        return code;

    }


    public List<Code> getLatest() {
        List<Code> codes = getAll();
        Collections.reverse(codes);
        List<Code> codesTenOrLow = new ArrayList<>();
        if (codes.size() > 10) {
            int index = 0;
            for (Code code : codes) {
                if (code.getTime() <= 0 && code.getViews() <= 0) {
                    index++;
                    codesTenOrLow.add(code);
                }
                if (index == 10) {
                    break;
                }
            }
        }

        return codesTenOrLow.size() > 0 ? codesTenOrLow : codes;
    }


}
