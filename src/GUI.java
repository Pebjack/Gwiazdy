import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;

public class GUI {
    public static ArrayList<Gwiazda> Gwiazdy = new ArrayList<>();
    public static Color nightSky = new Color(20,43,66);
    public static Dimension button = new Dimension(200,50);
    public static Color lightBlue = new Color(46,97,150);
    public static String[] hemiSpheres = {"PN", "PD"};

    public static JPanel declinPanel(){
        JPanel declinPanel = new JPanel();
        declinPanel.setBackground(nightSky);
        declinPanel.setLayout(new BoxLayout(declinPanel, BoxLayout.X_AXIS));

        JLabel declinLabel1 = new JLabel("Deklinacja:");
        declinLabel1.setForeground(Color.WHITE);
        declinPanel.add(declinLabel1);

        JTextField decliInput1 = new JTextField();
        decliInput1.setSize(130,40);
        decliInput1.setBackground(lightBlue);
        decliInput1.setForeground(Color.WHITE);
        declinPanel.add(decliInput1);

        JLabel degree = new JLabel(String.valueOf((char)248));
        degree.setForeground(Color.WHITE);
        declinPanel.add(degree);

        JTextField decliInput2 = new JTextField();
        decliInput2.setSize(130,40);
        decliInput2.setBackground(lightBlue);
        decliInput2.setForeground(Color.WHITE);
        declinPanel.add(decliInput2);

        JLabel minute = new JLabel(String.valueOf((char)39));
        minute.setForeground(Color.WHITE);
        declinPanel.add(minute);

        JTextField decliInput3 = new JTextField();
        decliInput3.setSize(130,40);
        decliInput3.setBackground(lightBlue);
        decliInput3.setForeground(Color.WHITE);
        declinPanel.add(decliInput3);

        JLabel second = new JLabel(String.valueOf((char)34));
        second.setForeground(Color.WHITE);
        declinPanel.add(second);

        return declinPanel;
    }

    public static JPanel rightAscendPanel(){
        JPanel rightAscPanel = new JPanel();
        rightAscPanel.setBackground(nightSky);
        rightAscPanel.setLayout(new BoxLayout(rightAscPanel, BoxLayout.X_AXIS));

        JLabel rightALabel1 = new JLabel("Rektascensja:");
        rightALabel1.setForeground(Color.WHITE);
        rightAscPanel.add(rightALabel1);

        JTextField rightAInput1 = new JTextField();
        rightAInput1.setSize(130,40);
        rightAInput1.setBackground(lightBlue);
        rightAInput1.setForeground(Color.WHITE);
        rightAscPanel.add(rightAInput1);

        JLabel hours = new JLabel("h");
        hours.setForeground(Color.WHITE);
        rightAscPanel.add(hours);

        JTextField rightAInput2 = new JTextField();
        rightAInput2.setSize(130,40);
        rightAInput2.setBackground(lightBlue);
        rightAInput2.setForeground(Color.WHITE);
        rightAscPanel.add(rightAInput2);

        JLabel minute = new JLabel(String.valueOf((char)39));
        minute.setForeground(Color.WHITE);
        rightAscPanel.add(minute);

        JTextField rightAInput3 = new JTextField();
        rightAInput3.setSize(130,40);
        rightAInput3.setBackground(lightBlue);
        rightAInput3.setForeground(Color.WHITE);
        rightAscPanel.add(rightAInput3);

        JLabel second = new JLabel(String.valueOf((char)34));
        second.setForeground(Color.WHITE);
        rightAscPanel.add(second);

        return rightAscPanel;
    }

    public static JScrollPane starList(){

        JScrollPane spisGwiazd = new JScrollPane(wyswietlTabele(Gwiazdy));
        spisGwiazd.setOpaque(true);
        spisGwiazd.getViewport().setBackground(nightSky);
        spisGwiazd.setBorder(BorderFactory.createEtchedBorder(new Color(43,91,140),new Color(31,66,102)));
        spisGwiazd.setBounds(0,0,1060,396);

        return spisGwiazd;
    }

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
        tabelaGwiazd.getTableHeader().setBackground(new Color(46,97,150));
        tabelaGwiazd.getTableHeader().setForeground(Color.WHITE);
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

    public static JPanel functionPanel(){
        JPanel functionPanel = new JPanel();
        functionPanel.setOpaque(true);
        functionPanel.setBackground(lightBlue);
        functionPanel.setLayout(new GridLayout(4,1));
        functionPanel.setBorder(BorderFactory.createEtchedBorder(new Color(43,91,140),new Color(31,66,102)));
        functionPanel.setBounds(660,396,400,296);

        JButton dodajGwiazde = new JButton("Dodaj gwiazdę");
        dodajGwiazde.setAlignmentX(Component.CENTER_ALIGNMENT);
        dodajGwiazde.setAlignmentY(Component.CENTER_ALIGNMENT);
        dodajGwiazde.setSize(button);
        functionPanel.add(dodajGwiazde);

        JButton usunGwiazde = new JButton("Usuń gwiazdę");
        usunGwiazde.setAlignmentX(Component.CENTER_ALIGNMENT);
        usunGwiazde.setAlignmentY(Component.CENTER_ALIGNMENT);
        usunGwiazde.setSize(button);
        functionPanel.add(usunGwiazde);

        JButton odswiezListe = new JButton("Odśwież listę");
        odswiezListe.setAlignmentX(Component.CENTER_ALIGNMENT);
        odswiezListe.setAlignmentY(Component.CENTER_ALIGNMENT);
        odswiezListe.setSize(button);
        functionPanel.add(odswiezListe);

        JButton wyszukiwanie = new JButton("Wyszukiwanie...");
        wyszukiwanie.setAlignmentX(Component.CENTER_ALIGNMENT);
        wyszukiwanie.setAlignmentY(Component.CENTER_ALIGNMENT);
        wyszukiwanie.setSize(button);
        functionPanel.add(wyszukiwanie);

        return functionPanel;
    }

    public static JPanel dataPanel(){
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new GridLayout(5,2));
        dataPanel.setOpaque(true);
        dataPanel.setBackground(new Color(20,43,66));
        dataPanel.setBorder(BorderFactory.createEtchedBorder(new Color(43,91,140),new Color(31,66,102)));
        dataPanel.setBounds(0,396,660,296);

        JPanel namePanel = new JPanel();
        namePanel.setBackground(nightSky);
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        JLabel nameLabel = new JLabel("Nazwa:");
        nameLabel.setForeground(Color.WHITE);
        namePanel.add(nameLabel);
        JTextField nameInput = new JTextField();
        nameInput.setBackground(lightBlue);
        nameInput.setSize(130,40);
        namePanel.add(nameInput);

        JPanel constPanel = new JPanel();
        constPanel.setBackground(nightSky);
        constPanel.setLayout(new BoxLayout(constPanel, BoxLayout.X_AXIS));
        JLabel constLabel = new JLabel("Gwiazdozbiór:");
        constLabel.setForeground(Color.WHITE);
        constPanel.add(constLabel);
        JTextField constInput = new JTextField();
        constInput.setBackground(lightBlue);
        constInput.setSize(130,40);
        constPanel.add(constInput);

        JPanel massPanel = new JPanel();
        massPanel.setBackground(nightSky);
        massPanel.setLayout(new BoxLayout(massPanel, BoxLayout.X_AXIS));
        JLabel massLabel = new JLabel("Masa:");
        massLabel.setForeground(Color.WHITE);
        massPanel.add(massLabel);
        JTextField massInput = new JTextField();
        massInput.setBackground(lightBlue);
        massInput.setSize(130,40);
        massPanel.add(massInput);

        JPanel tempPanel = new JPanel();
        tempPanel.setBackground(nightSky);
        tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.X_AXIS));
        JLabel tempLabel = new JLabel("Temperatura:");
        tempLabel.setForeground(Color.WHITE);
        tempPanel.add(tempLabel);
        JTextField tempInput = new JTextField();
        tempInput.setBackground(lightBlue);
        tempInput.setSize(130,40);
        tempPanel.add(tempInput);

        JPanel hemiPanel = new JPanel();
        hemiPanel.setBackground(nightSky);
        hemiPanel.setLayout(new BoxLayout(hemiPanel, BoxLayout.X_AXIS));
        JLabel hemiLabel = new JLabel("Półkula:");
        hemiLabel.setForeground(Color.WHITE);
        hemiPanel.add(hemiLabel);
        JComboBox hemiInput = new JComboBox(hemiSpheres);
        hemiInput.setSize(130,40);
        hemiPanel.add(hemiInput);

        JPanel declinPanel = declinPanel();
        JPanel rightAPanel = rightAscendPanel();

        JPanel distPanel = new JPanel();
        distPanel.setBackground(nightSky);
        distPanel.setLayout(new BoxLayout(distPanel, BoxLayout.X_AXIS));
        JLabel distLabel = new JLabel("Odległość:");
        distLabel.setForeground(Color.WHITE);
        distPanel.add(distLabel);
        JTextField distInput = new JTextField();
        distInput.setBackground(lightBlue);
        distInput.setSize(130,40);
        distPanel.add(distInput);

        JPanel oMagniPanel = new JPanel();
        oMagniPanel.setBackground(nightSky);
        oMagniPanel.setLayout(new BoxLayout(oMagniPanel, BoxLayout.X_AXIS));
        JLabel oMagniLabel = new JLabel("Obserwowalne magintudo:");
        oMagniLabel.setForeground(Color.WHITE);
        oMagniPanel.add(oMagniLabel);
        JTextField oMagniInput = new JTextField();
        oMagniInput.setBackground(lightBlue);
        oMagniInput.setSize(130,40);
        oMagniPanel.add(oMagniInput);

        dataPanel.add(namePanel);
        dataPanel.add(constPanel);
        dataPanel.add(massPanel);
        dataPanel.add(tempPanel);
        dataPanel.add(hemiPanel);
        dataPanel.add(declinPanel);
        dataPanel.add(rightAPanel);
        dataPanel.add(distPanel);
        dataPanel.add(oMagniPanel);

        return dataPanel;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JFrame oknoGlowne = new JFrame("Katalog Gwiazd");
                oknoGlowne.setLayout(null);
                oknoGlowne.setBackground(nightSky);
                oknoGlowne.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                oknoGlowne.setResizable(false);
                oknoGlowne.setPreferredSize(new Dimension(1060,740));

                JMenuBar menuGlowne = new JMenuBar();
                menuGlowne.setOpaque(false);
                menuGlowne.setPreferredSize(new Dimension(1060,20));

                JScrollPane spisGwiazd = starList();
                JPanel functionPanel = functionPanel();
                JPanel dataPanel = dataPanel();

                oknoGlowne.setJMenuBar(menuGlowne);
                oknoGlowne.getContentPane().add(spisGwiazd);
                oknoGlowne.getContentPane().add(functionPanel);
                oknoGlowne.getContentPane().add(dataPanel);
                oknoGlowne.pack();
                oknoGlowne.setVisible(true);
            }
        });
    }
}
