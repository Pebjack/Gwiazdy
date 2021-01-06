import javax.swing.*;

public class GUI {

    public static void main(String[] args){
        JFrame okno = new JFrame("Katalog Gwiazdowy");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(1280,700);
        JButton przycisk = new JButton("Naciś to");
        okno.getContentPane().add(przycisk);

        okno.setVisible(true);
    }

}
