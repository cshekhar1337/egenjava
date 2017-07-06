package cshekhar.springboot.Rules;
import cshekhar.springboot.DAO.AlertDao;
import cshekhar.springboot.model.Alert;
import cshekhar.springboot.model.Metric;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by cshekhar on 7/5/17.
 */
@Rule(name = "underweightrule", description = "Adds the entry to alert db if person is underweight.")
public class UnderWeightRule {
    @Autowired
    AlertDao al = new AlertDao();
    Metric metric;
    long base = 150;

    public UnderWeightRule(Metric metric) {
        this.metric = metric;
    }

    @Condition
    public boolean isUnderWeight() {
        System.out.println(metric);
        if((float)metric.getValue()  < 0.9 * base)
            return true;
        return false;
    }

    @Action
    public void addAlertUnderweight(){

        Alert alert = new Alert("UnderWeight", metric.getTimeStamp(), metric.getValue());
        System.out.println(alert);
        al.create(alert);

    }

}
