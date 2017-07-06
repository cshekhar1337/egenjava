package cshekhar.springboot.DAO;

import com.fasterxml.jackson.annotation.JsonInclude;
import cshekhar.springboot.MongodbConfig;
import cshekhar.springboot.Rules.OverWeightRule;
import cshekhar.springboot.Rules.UnderWeightRule;
import cshekhar.springboot.model.Metric;
import org.bson.types.ObjectId;
import org.easyrules.api.RulesEngine;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

/**
 * Created by cshekhar on 7/5/17.
 */

@Repository
public class MetricDao {
    Datastore datastore;
    RulesEngine rulesEngine;

    MetricDao() {
        datastore = MongodbConfig.getInstance().getDatastore();
        rulesEngine = aNewRulesEngine().build();
    }

    public String createMetric(Metric metric) {

        rulesEngine.registerRule(new OverWeightRule(metric));
        rulesEngine.registerRule(new UnderWeightRule(metric));

        rulesEngine.fireRules();
        rulesEngine.clearRules();

        datastore.save(metric);

        return metric.toString();
    }


    public List<Metric> read() {
        Query<Metric> query = datastore.find(Metric.class).order("timeStamp");


        return query.asList();
    }

    public List<Metric> readByRange(long startTime, long endTime) {
        Query<Metric> query = datastore.createQuery(Metric.class)
                .filter("timeStamp >=", startTime).filter("timeStamp <=", endTime).order("timeStamp");

        return query.asList();
    }
}
