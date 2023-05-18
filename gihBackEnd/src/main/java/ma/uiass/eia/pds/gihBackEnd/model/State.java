package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "Type", length = 255)
@JsonTypeInfo(use= JsonTypeInfo.Id.NAME,include = JsonTypeInfo.As.PROPERTY,property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value=F.class,name="F"),
        @JsonSubTypes.Type(value = NFCD.class,name="NFCD"),
        @JsonSubTypes.Type(value = NFLD.class,name="NFLD")
})
public abstract class State {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private static double A=100, B=100;
    private double x=0, y=0;
    @Column
    private String stateName;

    @OneToOne
    private Revision revision;

    @OneToOne(mappedBy = "state")
    @JsonIgnore
    private Ambulance ambulance;

    public State(double a, double b, double x, double y, String stateName, Revision revision) {
        A = a;
        B = b;
        this.x = x;
        this.y = y;
        this.stateName = stateName;
        this.revision = revision;
    }


    public Ambulance getAmbulance() {
        return ambulance;
    }

    public void setAmbulance(Ambulance ambulances) {
        this.ambulance = ambulances;
    }

    public State() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static double getA() {
        return A;
    }

    public static void setA(double a) {
        A = a;
    }

    public static double getB() {
        return B;
    }

    public static void setB(double b) {
        B = b;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Revision getRevision() {
        return revision;
    }

    public void setRevision(Revision revision) {
        this.revision = revision;
    }

}
