package models.persons;

import models.utils.BaseEntity;

import javax.persistence.Entity;

/**
 * Created by shane on 8/6/15.
 */
@Entity
public class Person extends BaseEntity {

    public String firstName;
    public String lastName;

}
