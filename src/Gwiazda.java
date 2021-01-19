import java.io.Serializable;
import java.util.ArrayList;

public class Gwiazda implements Serializable {
    // pola klasy
    private String name, catalogName, constel, hemisph, declin, rightAscen;
    private double obsMagnitudo, absMagnitudo, dist, temp, mass;

    // gettery stringów
    public String getName() {return name;}
    public String getCatName() {return catalogName;}
    public String getConstel() {return constel;}
    public String getHemisph() {return hemisph;}
    public String getDeclin() {return declin;}
    public String getRightAscen() {return rightAscen;}

    // settery stringów
    public void setName(String name) {this.name = name;}
    public void setCatName(String catName) {this.catalogName = catName;}
    public void setConstel(String constel) {this.constel = constel;}
    public void setHemisph(String hemisph) {this.hemisph = hemisph;}
    public void setDeclin(String declin) {this.declin = declin;}
    public void setRightAscen(String rightAscen) {this.rightAscen = rightAscen;}

    // gettery doubli

    public double getObsMagnitudo() {return obsMagnitudo;}
    public double getAbsMagnitudo() {return absMagnitudo;}
    public double getDist() {return dist;}
    public double getTemp() {return temp;}
    public double getMass() {return mass;}

    // settery doubli

    public void setObsMagnitudo(double obsMagnitudo) {this.obsMagnitudo = obsMagnitudo;}
    public void setAbsMagnitudo(double absMagnitudo) {this.absMagnitudo = absMagnitudo;}
    public void setDist(double dist) {this.dist = dist;}
    public void setTemp(double temp) {this.temp = temp;}
    public void setMass(double mass) {this.mass = mass;}

    public Gwiazda(String nam, String decl, String rasc, double omag, double dst, String con, String hms, double tmp, double mss){
        this.name = nam;
        this.constel = con;
        this.catalogName = (char)216 + ' ' + this.constel;
        this.declin = decl;
        this.rightAscen = rasc;
        this.obsMagnitudo = omag;
        this.dist = dst;
        this.absMagnitudo = this.obsMagnitudo - 5 * Math.log10(this.dist) + 5;
        this.hemisph = hms;
        this.temp = tmp;
        this.mass = mss;
    }

}
