package cshekhar.springboot.model;



/**
 * Created by cshekhar on 7/5/17.
 */

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.utils.IndexDirection;
import org.springframework.data.annotation.Id;


@Entity("alerts")
public class Alert {



    @Property("type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Property("timeStamp")
    @Indexed(value= IndexDirection.ASC)
    private long timeStamp;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Property("weight")
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Alert(String type, long timeStamp, int weight) {
        this.timeStamp = timeStamp;
        this.type = type;
        this.weight = weight;
    }
    public Alert(){

    }
    @Override
    public String toString(){
        return "------Type:" + getType() + "---timeStamp"+ getTimeStamp() + "----weight" + getWeight();

    }


}

