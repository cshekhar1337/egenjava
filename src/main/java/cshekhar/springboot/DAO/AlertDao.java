package cshekhar.springboot.DAO;

import cshekhar.springboot.MongodbConfig;
import cshekhar.springboot.model.Alert;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cshekhar on 7/5/17.
 */
@Repository
public class AlertDao {
    Datastore datastore;

    public AlertDao() {
        datastore = MongodbConfig.getInstance().getDatastore();
    }

    public String create(Alert alert) {
        Datastore datastore = MongodbConfig.getInstance().getDatastore();

        datastore.save(alert);

        return alert.toString();
    }

    public List<Alert> read() {
        Query<Alert> query = datastore.createQuery(Alert.class).order("timeStamp");

        return query.asList();
    }

    public List<Alert> readByRange(long startTime, long endTime) {
        Query<Alert> query = datastore.createQuery(Alert.class)
                .filter("timeStamp >=", startTime).filter("timeStamp <=", endTime).order("timeStamp");

        return query.asList();
    }


}
