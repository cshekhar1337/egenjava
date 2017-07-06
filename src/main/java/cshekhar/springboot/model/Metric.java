package cshekhar.springboot.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.utils.IndexDirection;
import org.springframework.data.annotation.Id;

/**
 * Created by cshekhar on 7/5/17.
 */

@Entity("metrics")
public class Metric {



    @Property("timeStamp")
    @Indexed(value= IndexDirection.ASC)
    private long timeStamp;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Property("value")
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {

        return "Metric [TimeStamp = " + timeStamp + ", " + "Value = " + value + "]";
    }
}
