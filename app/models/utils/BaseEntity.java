package models.utils;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by shane on 8/6/15.
 */
@MappedSuperclass
public class BaseEntity extends BaseModel {

    public Date createdAt;
    public Date updatedAt;

    @Override
    public void save() {
        // Add logic
        super.save();
    }

    @Override
    public void update() {
        // Add logic
        super.update();
    }

    public void delete() {
        // Add logic
        this.deleted = true;
        this.update();
    }
}
