import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class GUI {

    public static JTable wyswietlTabele(ArrayList<Gwiazda> gwiazdy){
        String[] kolumny = {"Nazwa",
                "Nazwa Katalogowa",
                "Deklinacja",
                "Rektascensja",
                "Obserwowalna wielkość gwiazdowa",
                "Absolutna wielkość gwiazdowa",
                "Odległość",
                "Gwiazdozbiór",
                "Półkula",
                "Temperatura",
                "Masa"
        };
        DefaultTableModel modelTablicy = new DefaultTableModel(kolumny, 0);
        modelTablicy.setColumnIdentifiers(kolumny);
        JTable tabelaGwiazd = new JTable(modelTablicy);
        for(int i = 0; i < gwiazdy.size(); i++){
            Object[] dane = {
                    gwiazdy.get(i).getName(),
                    gwiazdy.get(i).getCatName(),
                    gwiazdy.get(i).getDeclin(),
                    gwiazdy.get(i).getRightAscen(),
                    gwiazdy.get(i).getObsMagnitudo(),
                    gwiazdy.get(i).getAbsMagnitudo(),
                    gwiazdy.get(i).getDist(),
                    gwiazdy.get(i).getConstel(),
                    gwiazdy.get(i).getHemisph(),
                    gwiazdy.get(i).getTemp(),
                    gwiazdy.get(i).getMass(),
            };
            modelTablicy.addRow(dane);

        }


        return tabelaGwiazd;
    }



    public static void addStar(){

    }

    public static void main(String[] args){
        ArrayList<Gwiazda> Gwiazdy = new ArrayList<>();
        JButton przycisk = new JButton("Dodaj gwiazdę");

        JFrame okno = new JFrame("Katalog Gwiazdowy");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(1280,700);

        Gwiazdy.add(new Gwiazda("ABC123","60.90.35.15","15.35.20",10.00, 17, "Centauri", "PD", 2000000, 10));

        okno.add(przycisk);
        okno.add(wyswietlTabele(Gwiazdy));
        okno.setVisible(true);

    }


}
