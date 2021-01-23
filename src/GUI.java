import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.*;

// sprawdzenie czy w polu został wprowadzony int
class IntValidation extends InputVerifier {
    @Override
    public boolean verify(JComponent input) throws InputMismatchException {
        String content = ((JTextField) input).getText();
        boolean parse = true;
        if (((JTextField) input).getText().length() != 0) {
            try {
                int value = Integer.parseInt(content);
                parse = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Podano niewłaściwy rodzaj danych! Podaj liczbę całkowitą...");
                ((JTextField) input).setText("");
                parse = false;
            }
        }
        return parse;
    }
}

// sprawdzenie czy w polu został wprowadzony double
class DoubleValidation extends InputVerifier{
    @Override
    public boolean verify(JComponent input) {
        String content = ((JTextField) input).getText();
        String goodString = content.replace(',','.');
        boolean parse = true;
        if (((JTextField) input).getText().length() != 0) {
            try {
                double value = Double.parseDouble(goodString);
                parse = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Podano niewłaściwy rodzaj danych! Podaj dodatnią liczbę rzeczywistą...");
                ((JTextField) input).setText("");
                parse = false;
            }
        }
        return parse;
    }
}

// interfejs użytkownika
public class GUI {

    public static char[] greekCharset = {
            214, 215, 216, 221, 222, 224, 225, 226, 227,
            228, 229, 230, 231, 232, 233, 234, 235, 236,
            237, 238, 242, 243, 244, 246, 250
    };
    public static IntValidation sprawdzInt = new IntValidation();
    public static DoubleValidation sprawdzDouble = new DoubleValidation();
    public static ArrayList<Gwiazda> gwiazdy = new ArrayList<>(); // lista gwiazd, na niej opiera się działanie programu
    public static Color nightSky = new Color(20,43,66);
    public static Dimension button = new Dimension(200,50);
    public static Color lightBlue = new Color(46,97,150); // kolory GUI
    public static String[] hemiSpheres = {null, "PN", "PD"}; // lista wartości do wyboru w polu "półkula"

    public static String[] declin = new String[3];
    public static String[] rightAsc = new String[3];
    public static String[] nazwa = new String[1];
    public static String[] gwiazdozbior = new String[1];
    public static double[] masa = new double[1];
    public static double[] temperatura = new double[1];
    public static String[] polkula = new String[1];
    public static double[] odleglosc = new double[1];
    public static double[] magnitudo = new double[1];

    // funkcja tworzy nową gwiadzę i dodaje ją do listy
    public static void addStar() {
        String deklinacja = declin[0] + declin[1] + declin[2];
        String rektascensja = rightAsc[0] + rightAsc[1] + rightAsc[2];
        int numerGwiazdy = 0;
        if (gwiazdy != null) {
            for (Gwiazda g : gwiazdy) {
                if (g.getConstel().equals(gwiazdozbior[0]) && g.getConstel() == null ) {
                    numerGwiazdy++;
                }
            }
        }

        gwiazdy.add(new Gwiazda(nazwa[0],
                (greekCharset[numerGwiazdy] + " " + gwiazdozbior[0]),
                gwiazdozbior[0],
                polkula[0],
                deklinacja,
                rektascensja,
                magnitudo[0],
                odleglosc[0],
                temperatura[0],
                masa[0]));
    }

    public static void removeStar(){

    }

    public static void displayStars(DefaultTableModel model){
        model.setRowCount(0);
        for (Gwiazda gwiazda : gwiazdy) {
            Object[] dane = {
                    gwiazda.getName(),
                    gwiazda.getCatName(),
                    gwiazda.getDeclin(),
                    gwiazda.getRightAscen(),
                    gwiazda.getObsMagnitudo(),
                    gwiazda.getAbsMagnitudo(),
                    gwiazda.getDist(),
                    gwiazda.getConstel(),
                    gwiazda.getHemisph(),
                    gwiazda.getTemp(),
                    gwiazda.getMass(),
            };
            model.addRow(dane);
        };
    }

    public static void searchConst(){

    }

    public static void searchDist(){

    }

    public static void searchTemp(){

    }

    public static void searchAbsMagnit(){

    }

    public static void searchHemisphere(){

    }

    public static void searchSupernova(){

    }

    // metoda uzupełnia elementy typu int list pomocniczych dla pól deklinacja i rektascensja (osobno stopnie/godziny i minuty)
    public static void setIntValue(JTextField field, double border, String[] list, int i, char c){

        try {
            if (field.isValid()) {
                int d1 = Integer.parseInt(field.getText()); // definicja zmiennej pomocniczej int dla określenia wartości podanej w polu
                if (d1 >= 0 && d1 <= border) { // sprawdzenie wartości w przypadku pomyślnej walidacji pola
                    list[i] = field.getText(); // dodanie elementu listy pomocniczej dla pól deklinacja i rektascensja
                    list[i] += c;
                } else {
                    JOptionPane.showMessageDialog(null, ("Mozna podać tylko 0 - " + border + "!"));
                    field.setText("");
                }
            }
        } catch (NumberFormatException ignored) {

        }
    }

    // metoda analogiczna dla elementów typu double (sekundy)
    public static void setDoubleValue(JTextField field, double border, String[] list, int i){

        try {
            if (field.isValid()) {
                double d1 = Double.parseDouble(field.getText());
                if (d1 >= 0 && d1 <= border) {
                    list[i] = field.getText();
                    list[i] += (char)34;
                } else {
                    JOptionPane.showMessageDialog(null, ("Mozna podać tylko 0 - " + border + "!"));
                    field.setText("");
                }
            }
        } catch (NumberFormatException ignored) {

        }
    }

    // panel dla wprowadzania danych dla pola deklinacja - wydzielony z uwagi na złożoność
    public static JPanel declinPanel(){
        JPanel declinPanel = new JPanel();
        declinPanel.setBackground(nightSky);
        declinPanel.setLayout(new BoxLayout(declinPanel, BoxLayout.X_AXIS));

        JLabel declinLabel1 = new JLabel("Deklinacja:");
        declinLabel1.setForeground(Color.WHITE);
        declinPanel.add(declinLabel1);

        JTextField decliInput1 = new JTextField(); // pole wprowadzania stopni
        decliInput1.setSize(130,40);
        decliInput1.setInputVerifier(sprawdzInt);
        decliInput1.setBackground(lightBlue);
        decliInput1.setForeground(Color.WHITE);
        declinPanel.add(decliInput1);

        JLabel degree = new JLabel(String.valueOf((char)248));
        degree.setForeground(Color.WHITE);
        declinPanel.add(degree);

        JTextField decliInput2 = new JTextField(); // pole wprowadzania minut
        decliInput2.setSize(130,40);
        decliInput2.setInputVerifier(sprawdzInt);
        decliInput2.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e){ }
            @Override
            public void focusLost(FocusEvent e) {
                setIntValue(decliInput2, 59, declin, 1, (char)39);
            }
        });
        decliInput2.setBackground(lightBlue);
        decliInput2.setForeground(Color.WHITE);
        declinPanel.add(decliInput2);

        JLabel minute = new JLabel(String.valueOf((char)39));
        minute.setForeground(Color.WHITE);
        declinPanel.add(minute);

        JTextField decliInput3 = new JTextField(); // pole wprowadzania sekund
        decliInput3.setSize(130,40);
        decliInput3.setInputVerifier(sprawdzDouble);
        decliInput3.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e){}

            @Override
            public void focusLost(FocusEvent e) {
                setDoubleValue(decliInput3, 59.99, declin, 2);
            }
        });
        decliInput1.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e){}

            @Override // wartości są zatwierdzane przy "odkliknięciu" pola
            public void focusLost(FocusEvent e) {
                try {
                    if (decliInput1.isValid()) {
                        int d1 = Integer.parseInt(decliInput1.getText());
                        if (d1 == 90){ // jeżeli użytkownik poda w polu stopni maksymalną wartośc, pozostałe pola automatycznie przyjmują wartość 0 i ich edycja jest blokowana
                            decliInput2.setText("0");
                            decliInput2.setEnabled(false);
                            setIntValue(decliInput2, 59, declin, 1, (char)39);
                            decliInput3.setText("0,0");
                            decliInput3.setEnabled(false);
                            setDoubleValue(decliInput3, 59.99, declin, 2);
                        }
                        else{ // w przypadku wprowadzenia innej wartości następuje odblokowanie pól
                            decliInput2.setEnabled(true);
                            decliInput3.setEnabled(true);
                        }
                        if (d1 >= 0 && d1 <= 90) {
                            declin[0] = decliInput1.getText();
                            declin[0] += (char)248;
                        } else {
                            JOptionPane.showMessageDialog(null, "Mozna podać tylko 0-90 !");
                            decliInput1.setText("");
                        }
                    }
                } catch (NumberFormatException ignored) {

                }
            }
        });
        decliInput3.setBackground(lightBlue);
        decliInput3.setForeground(Color.WHITE);

        declinPanel.add(decliInput3);

        JLabel second = new JLabel(String.valueOf((char)34));
        second.setForeground(Color.WHITE);
        declinPanel.add(second);

        return declinPanel;
    }

    // j/w dla pola rektascensja
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
        rightAInput2.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e){}

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (rightAInput2.isValid()) {
                        int d1 = Integer.parseInt(rightAInput2.getText());
                        if (d1 >= 0 && d1 <= 59) {
                            rightAsc[1] = rightAInput2.getText();
                            rightAsc[1] += "m";
                        } else {
                            JOptionPane.showMessageDialog(null, "Mozna podać tylko 0-24 godziny !");
                            rightAInput2.setText("");
                        }
                    }
                } catch (NumberFormatException ignored) {

                }
            }
        });
        rightAInput2.setBackground(lightBlue);
        rightAInput2.setForeground(Color.WHITE);
        rightAscPanel.add(rightAInput2);

        JLabel minute = new JLabel("m");
        minute.setForeground(Color.WHITE);
        rightAscPanel.add(minute);

        JTextField rightAInput3 = new JTextField();
        rightAInput3.setSize(130,40);
        // ustala wartość pola
        rightAInput3.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e){}

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (rightAInput3.isValid()) {
                        int d1 = Integer.parseInt(rightAInput3.getText());
                        if (d1 >= 0 && d1 <= 59) {
                            rightAsc[2] = rightAInput3.getText();
                            rightAsc[2] += "s";
                        } else {
                            JOptionPane.showMessageDialog(null, "Mozna podać tylko 0-24 godziny !");
                            rightAInput3.setText("");
                        }
                    }
                } catch (NumberFormatException ignored) {

                }
            }
        });
        // ustala wartosć pola godzin
        rightAInput1.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e){}

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (rightAInput1.isValid()) {
                        int r1 = Integer.parseInt(rightAInput1.getText());
                        if (r1 == 24){
                            rightAInput2.setText("0");
                            rightAInput2.setEnabled(false);
                            setIntValue(rightAInput2, 59, rightAsc, 1, (char)39);
                            rightAInput3.setText("0,0");
                            rightAInput3.setEnabled(false);
                            setDoubleValue(rightAInput3, 59.99, rightAsc, 2);
                        }
                        else{
                            rightAInput2.setEnabled(true);
                            rightAInput3.setEnabled(true);
                        }
                        if (r1 >= 0 && r1 <= 24) {
                            rightAsc[0] = rightAInput1.getText();
                            rightAsc[0] += (char)248;
                        } else {
                            JOptionPane.showMessageDialog(null, "Mozna podać tylko 0-24 !");
                            rightAInput1.setText("");
                        }
                    }
                } catch (NumberFormatException ignored) {

                }
            }
        });
        rightAInput3.setBackground(lightBlue);
        rightAInput3.setForeground(Color.WHITE);
        rightAscPanel.add(rightAInput3);
        JLabel second = new JLabel("s");
        second.setForeground(Color.WHITE);
        rightAscPanel.add(second);

        return rightAscPanel;
    }



    // panel do wprowadzania danych, tu się dzieje prawdziwa magia
    public static JPanel dataPanel(){
        JPanel dataPanel = new JPanel();
        dataPanel.setFocusable(true);
        dataPanel.setLayout(new GridLayout(5,2));
        dataPanel.setOpaque(true);
        dataPanel.setBackground(new Color(20,43,66));
        dataPanel.setBorder(BorderFactory.createEtchedBorder(new Color(43,91,140),new Color(31,66,102)));
        dataPanel.setBounds(0,396,660,296);


        //panel wprowadzania dla pola nazwa
        JPanel namePanel = new JPanel();
        namePanel.setBackground(nightSky);
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        JLabel nameLabel = new JLabel("Nazwa:");
        nameLabel.setForeground(Color.WHITE);
        namePanel.add(nameLabel);
        JTextField nameInput = new JTextField();
        nameInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {}

            @Override
            public void focusLost(FocusEvent e) {
                if(nameInput.getText().length() != 0){
                    nazwa[0] = nameInput.getText();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Nazwa gwiazdy nie może być pusta!");
                }
            }
        });
        nameInput.setBackground(lightBlue);
        nameInput.setSize(130,40);
        namePanel.add(nameInput);

        // panel wprowadzania dla pola gwiazdozbioru
        JPanel constPanel = new JPanel();
        constPanel.setBackground(nightSky);
        constPanel.setLayout(new BoxLayout(constPanel, BoxLayout.X_AXIS));
        JLabel constLabel = new JLabel("Gwiazdozbiór:");
        constLabel.setForeground(Color.WHITE);
        constPanel.add(constLabel);
        JTextField constInput = new JTextField();
        constInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { }

            @Override
            public void focusLost(FocusEvent e) {
                if(constInput.getText().length() != 0){
                    gwiazdozbior[0] = constInput.getText();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Podaj nazwę gwiazdozbioru!");
                }
            }
        });
        constInput.setBackground(lightBlue);
        constInput.setSize(130,40);
        constPanel.add(constInput);

        // panel wprowadzania dla pola masa
        JPanel massPanel = new JPanel();
        massPanel.setBackground(nightSky);
        massPanel.setLayout(new BoxLayout(massPanel, BoxLayout.X_AXIS));
        JLabel massLabel = new JLabel("Masa:");
        massLabel.setForeground(Color.WHITE);
        massPanel.add(massLabel);
        JTextField massInput = new JTextField();
        massInput.setBackground(lightBlue);
        massInput.setInputVerifier(sprawdzDouble);
        massInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { }

            @Override
            public void focusLost(FocusEvent e) {
                if(massInput.isValid() && massInput.getText().length() != 0){
                    double m1 = Integer.parseInt(massInput.getText());
                    if(m1 >= 0.1 && m1 <= 50){
                        masa[0] = m1;
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Podano dane z niewłasciwego zakresu! Podaj dane z zakresu 0.1-50");
                        massInput.setText("");
                    }
                }
            }
        });
        massInput.setSize(130,40);
        massPanel.add(massInput);
        JLabel massUnit = new JLabel("MSol");
        massUnit.setForeground(Color.WHITE);
        massPanel.add(massUnit);

        // panel wprowadzania dla pola temperatura
        JPanel tempPanel = new JPanel();
        tempPanel.setBackground(nightSky);
        tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.X_AXIS));
        JLabel tempLabel = new JLabel("Temperatura:");
        tempLabel.setForeground(Color.WHITE);
        tempPanel.add(tempLabel);
        JTextField tempInput = new JTextField();
        tempInput.setInputVerifier(sprawdzDouble);
        tempInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { }

            @Override
            public void focusLost(FocusEvent e) {
                if(tempInput.isValid() && tempInput.getText().length() != 0){
                    double t1 = Double.parseDouble(tempInput.getText());
                    if(t1 >= 2000.0){
                        temperatura[0] = t1;
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Temperatura nie może być niższa od 2000 stopni!");
                        tempInput.setText("");
                    }
                }
            }
        });
        tempInput.setBackground(lightBlue);
        tempInput.setSize(130,40);
        tempPanel.add(tempInput);
        JLabel tempUnit = new JLabel((char)248 + "C");
        tempUnit.setForeground(Color.WHITE);
        tempPanel.add(tempUnit);

        // panel wprowadzania dla pola półkula
        JPanel hemiPanel = new JPanel();
        hemiPanel.setBackground(nightSky);
        hemiPanel.setLayout(new BoxLayout(hemiPanel, BoxLayout.X_AXIS));
        JLabel hemiLabel = new JLabel("Półkula:");
        hemiLabel.setForeground(Color.WHITE);
        hemiPanel.add(hemiLabel);
        JComboBox hemiInput = new JComboBox(hemiSpheres);
        hemiInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(hemiInput.getSelectedItem() != null){
                    polkula[0] = hemiInput.getSelectedItem().toString();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Wymagany wybór pólkuli!");
                }
            }
        });
        hemiInput.setSize(130,40);
        hemiPanel.add(hemiInput);

        // panele deklinacji i rektascensji
        JPanel declinPanel = declinPanel();
        JPanel rightAPanel = rightAscendPanel();

        // panel wprowadzania dla pola odległosć
        JPanel distPanel = new JPanel();
        distPanel.setBackground(nightSky);
        distPanel.setLayout(new BoxLayout(distPanel, BoxLayout.X_AXIS));
        JLabel distLabel = new JLabel("Odległość:");
        distLabel.setForeground(Color.WHITE);
        distPanel.add(distLabel);
        JTextField distInput = new JTextField();
        distInput.setInputVerifier(sprawdzDouble);
        distInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if(distInput.isValid() && distInput.getText().length() != 0){
                    double d1 = Double.parseDouble(distInput.getText());
                    if(d1 > 0){
                        odleglosc[0] = d1;
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Odległość musi być dodatnia!");
                        tempInput.setText("");
                    }
                }
            }
        });
        distInput.setBackground(lightBlue);
        distInput.setSize(130,40);
        distPanel.add(distInput);
        JLabel distUnit = new JLabel("Ly");
        distUnit.setForeground(Color.WHITE);
        distPanel.add(distUnit);

        // panel wprowadzania dla pola obserwowalnej wielkości gwiazdowej
        JPanel oMagniPanel = new JPanel();
        oMagniPanel.setBackground(nightSky);
        oMagniPanel.setLayout(new BoxLayout(oMagniPanel, BoxLayout.X_AXIS));
        JLabel oMagniLabel = new JLabel("Obserwowalne magintudo:");
        oMagniLabel.setForeground(Color.WHITE);
        oMagniPanel.add(oMagniLabel);
        JTextField oMagniInput = new JTextField();
        oMagniInput.setInputVerifier(sprawdzDouble);
        oMagniInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if(oMagniInput.isValid() && oMagniInput.getText().length() != 0){
                    double m1 = Double.parseDouble(oMagniInput.getText());
                    if(m1 >= -26.74 && m1 <= 15.00 ){
                        magnitudo[0] = m1;
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Odległość musi być dodatnia!");
                        oMagniInput.setText("");
                    }
                }
            }
            });
        oMagniInput.setBackground(lightBlue);
        oMagniInput.setSize(130,40);
        oMagniPanel.add(oMagniInput);

        dataPanel.add(namePanel);
        dataPanel.add(declinPanel);
        dataPanel.add(constPanel);
        dataPanel.add(rightAPanel);
        dataPanel.add(oMagniPanel);
        dataPanel.add(tempPanel);
        dataPanel.add(hemiPanel);
        dataPanel.add(distPanel);
        dataPanel.add(massPanel);

        return dataPanel;
    }

    // inicjacja głównego interfejsu
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

                JScrollPane spisGwiazd = new JScrollPane(tabelaGwiazd);
                spisGwiazd.setOpaque(true);
                spisGwiazd.getViewport().setBackground(nightSky);
                spisGwiazd.setBorder(BorderFactory.createEtchedBorder(new Color(43,91,140),new Color(31,66,102)));
                spisGwiazd.setBounds(0,0,1060,396);

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
                dodajGwiazde.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addStar();
                        displayStars(modelTablicy);
                    }
                });
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

                JPanel dataPanel = dataPanel();

                oknoGlowne.setJMenuBar(menuGlowne);
                oknoGlowne.getContentPane().add(spisGwiazd);
                displayStars(modelTablicy);
                oknoGlowne.getContentPane().add(functionPanel);
                oknoGlowne.getContentPane().add(dataPanel);
                oknoGlowne.pack();
                oknoGlowne.setVisible(true);


            }
        });
    }
}
