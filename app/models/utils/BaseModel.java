package models.utils;


import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by shane on 8/6/15.
 */
@MappedSuperclass
public class BaseModel extends Model {

    @Id
    public long id;

    @JsonIgnore
    @Constraints.Required
    public boolean deleted;
}
