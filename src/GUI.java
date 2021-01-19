import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;

public class GUI {

    public static JTable wyswietlTabele(ArrayList<Gwiazda> gwiazdy){
        String[] kolumny = {
                "Nazwa",
                "Nazwa Katalogowa",
                "Deklinacja",
                "Rektascensja",
                "Obserwowalne Magnitudo",
                "Absolutne Magnitudo",
                "Odległość",
                "Gwiazdozbiór",
                "Półkula",
                "Temperatura",
                "Masa"
        };
        DefaultTableModel modelTablicy = new DefaultTableModel(kolumny, 0);
        JTable tabelaGwiazd = new JTable(modelTablicy);
        tabelaGwiazd.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabelaGwiazd.setOpaque(true);

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



    public static void main(String[] args){
        ArrayList<Gwiazda> Gwiazdy = new ArrayList<>();
        //new Color(20,43,66)
        Dimension button = new Dimension(200,50);

        JFrame oknoGlowne = new JFrame("Katalog Gwiazd");
        oknoGlowne.setLayout(null);
        oknoGlowne.setBackground(new Color(20,43,66));
        oknoGlowne.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        oknoGlowne.setResizable(false);
        oknoGlowne.setPreferredSize(new Dimension(1060,740));

        JMenuBar menuGlowne = new JMenuBar();
        menuGlowne.setOpaque(false);
        menuGlowne.setPreferredSize(new Dimension(1060,20));

        JScrollPane spisGwiazd = new JScrollPane(wyswietlTabele(Gwiazdy));
        spisGwiazd.setOpaque(true);
        spisGwiazd.getViewport().setBackground(new Color(20,43,66));
        spisGwiazd.setBorder(BorderFactory.createEtchedBorder(new Color(43,91,140),new Color(31,66,102)));
        spisGwiazd.setBounds(0,0,1060,396);

        JPanel functionPanel = new JPanel();
        functionPanel.setOpaque(true);
        functionPanel.setBackground(new Color(20,43,66));
        functionPanel.setBorder(BorderFactory.createEtchedBorder(new Color(43,91,140),new Color(31,66,102)));
        functionPanel.setBounds(660,396,400,296);

        JButton dodajGwiazde = new JButton("Dodaj gwiazdę");
        dodajGwiazde.setPreferredSize(button);
        functionPanel.add(dodajGwiazde);

        JPanel dataPanel = new JPanel();
        dataPanel.setOpaque(true);
        dataPanel.setBackground(new Color(20,43,66));
        dataPanel.setBorder(BorderFactory.createEtchedBorder(new Color(43,91,140),new Color(31,66,102)));
        dataPanel.setBounds(0,396,660,296);


        oknoGlowne.setJMenuBar(menuGlowne);
        oknoGlowne.getContentPane().add(spisGwiazd);
        oknoGlowne.getContentPane().add(functionPanel);
        oknoGlowne.getContentPane().add(dataPanel);
        oknoGlowne.pack();
        oknoGlowne.setVisible(true);


    }


}
