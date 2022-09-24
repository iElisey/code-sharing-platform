package platform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import platform.views.Views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Code {


    @JsonView(Views.Id.class)
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @JsonView(Views.FullWithoutId.class)
    private String code;

    @JsonView(Views.FullWithoutId.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime date;

    @JsonView(Views.FullWithoutId.class)
    private Long time;

    @JsonView(Views.FullWithoutId.class)
    private Long views;

    @JsonIgnore
    private Boolean timeRestricted; @JsonIgnore
    private Boolean viewsRestricted;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }


    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", views=" + views +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code code1 = (Code) o;
        return id == code1.id && Objects.equals(code, code1.code) && Objects.equals(date, code1.date);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, code, date);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Code(String code, Long time, Long views) {
        this.code = code;
        this.date = LocalDateTime.now();
        this.time = time;
        this.views = views;
        this.timeRestricted= this.time > 0;
        this.viewsRestricted= this.views > 0;
    }

    public Code() {
    }

    public Boolean getTimeRestricted() {
        return timeRestricted;
    }

    public void setTimeRestricted(Boolean timeRestricted) {
        this.timeRestricted = timeRestricted;
    }

    public Boolean getViewsRestricted() {
        return viewsRestricted;
    }

    public void setViewsRestricted(Boolean viewsRestricted) {
        this.viewsRestricted = viewsRestricted;
    }
}
