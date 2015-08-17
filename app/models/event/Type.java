package models.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.utils.BaseModel;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by shane on 8/17/15.
 */
@Entity
public class Type extends BaseModel {

    public static Finder<Long, Type> find = new Finder<>(Type.class);

    @Constraints.Required
    public String name;

    @JsonIgnore
    @OneToMany(mappedBy = "type")
    public List<EventType> events;

    public Type(String name) {
        this.name = name;
    }
}
