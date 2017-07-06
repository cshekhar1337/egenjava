package cshekhar.springboot;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

/**
 * Created by cshekhar on 7/5/17.
 */
public class MongodbConfig {



    private static MongodbConfig mongoConfig = new MongodbConfig();

    private Datastore datastore = null;

    private MongodbConfig() {
        Morphia morphia = new Morphia();
        try {
            datastore = morphia.createDatastore(new MongoClient(), "egenWeightSensor");
            datastore.ensureIndexes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Datastore getDatastore() {
        return datastore;
    }

    public static MongodbConfig getInstance() {
        return mongoConfig;
    }
}

