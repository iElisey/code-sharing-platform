package platform.dto;

import java.util.Objects;

public class CodeDto {
    private String code;
    private Long time;
    private Long views;

    @Override
    public String toString() {
        return "CodeDto{" +
                "code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeDto codeDto = (CodeDto) o;
        return Objects.equals(code, codeDto.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CodeDto() {
    }

    public CodeDto(String code) {
        this.code = code;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}

