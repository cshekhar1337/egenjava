package cshekhar.springboot.Rules;

import cshekhar.springboot.DAO.AlertDao;
import cshekhar.springboot.model.Alert;
import cshekhar.springboot.model.Metric;
import org.easyrules.annotation.Rule;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by cshekhar on 7/5/17.
 */
@Rule(name = "overweightrule", description = "Adds the entry to alert db if person is overweight.")
public class OverWeightRule {
    Metric metric;

    long base = 150;

    AlertDao al = new AlertDao();

    public OverWeightRule(Metric metric) {
        this.metric = metric;
    }

    @Condition
    public boolean isOverWeight() {

        System.out.println("------------++++++++________" + base);
        if((float)metric.getValue()  > 1.1 * base)
            return true;
        return false;
    }

    @Action
    public void addAlertOverweight(){


        Alert alert = new Alert("OverWeight", metric.getTimeStamp(), metric.getValue());
        al.create(alert);

    }

}
