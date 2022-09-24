package platform.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import platform.model.Code;
import platform.service.CodeService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class CodeComponent {

    @Autowired
    private CodeService codeService;

    @Scheduled(fixedRate = 1000)
    public void getSeconds() {
        List<Code> all = codeService.getAll();
        for (Code code : all) {
            if (code.getTimeRestricted()) {
                long between = ChronoUnit.SECONDS.between(code.getDate(), LocalDateTime.now());
                if (between > code.getTime()) {
                    codeService.remove(code);
                } else {
                    code.setTime(code.getTime() - 1);
                }
                codeService.save(code);
            }
        }
    }
}
