package services.model;

import com.avaje.ebean.Model;

/**
 * Created by shane on 8/8/15.
 */
public class Service<T extends Model> {
    public final Model.Finder<Long, T> repo;

    public Service(Model.Finder<Long, T> repo) {
        this.repo = repo;
    }
}
