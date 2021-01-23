/* Paweł Jackowski

Program.java

Wersja 6.5
 */
import java.io.*;
import java.util.*;

public class Program {

    public static ArrayList<Gwiazda> katalogGwiazd = new ArrayList<>(); //lista Gwiazd używana we wszystkich metodach
    public static char[] greckieLitery = {'\u03b1', '\u03b2', '\u03b3', '\u03b4', '\u03b5', '\u03b6', '\u03b7',
            '\u03b8', '\u03b9', '\u03ba', '\u03bb', '\u03bc', '\u03bd', '\u03be', '\u03bf', '\u03c0',
            '\u03c1', '\u03c2', '\u03c3', '\u03c4', '\u03c5', '\u03c6', '\u03c7', '\u03c8', '\u03c9'}; //lista z greckim alfabetem do nadawania nazwy.

    // wyświetlenie wszystkich gwiazd w bazie
    public static void wyswietlGwiazdy(ArrayList<Gwiazda> lista) {
        System.out.print("Numer |");
        System.out.printf("%8s", "Nazwa |");
        System.out.printf("%19s", "Nazwa katalogowa |");
        System.out.printf("%15s", "Gwiazdozbiór |");
        System.out.printf("%10s", "Półkula |");
        System.out.printf("%13s", "Deklinacja |");
        System.out.printf("%15s", "Rektascensja |");
        System.out.printf("%24s", "Obserwowalna wielkosć |");
        System.out.printf("%21s", "Wielkość absolutna |");
        System.out.printf("%7s", "Masa |");
        System.out.printf("%21s", "Odległość od ziemi |");
        System.out.printf("%14s", "Temperatura |");
        System.out.println();
        if (lista.size() != 0) {
            for (int i = 0; i < lista.size(); i++) {
                System.out.print(i + 1);
                System.out.printf("%6c", '|');
                System.out.printf("%6s", lista.get(i).getName());
                System.out.printf("%6c", '|');
                System.out.printf("%6s", lista.get(i).getCatName());
                System.out.printf("%6c", '|');
                System.out.printf("%6s", lista.get(i).getConstel());
                System.out.printf("%6c", '|');
                System.out.printf("%5s", lista.get(i).getHemisph());
                System.out.printf("%3c", '|');
                System.out.printf("%13s", lista.get(i).getDeclin());
                System.out.printf("%2c", '|');
                System.out.printf("%13s", lista.get(i).getRightAscen());
                System.out.printf("%3c", '|');
                System.out.printf("%10s", lista.get(i).getObsMagnitudo());
                System.out.printf("%10c", '|');
                System.out.printf("%21s", lista.get(i).getAbsMagnitudo());
                System.out.printf("%3c", '|');
                System.out.printf("%2s", lista.get(i).getMass());
                System.out.printf("%4c", '|');
                System.out.printf("%13s", lista.get(i).getDist());
                System.out.printf("%6c", '|');
                System.out.printf("%8s", lista.get(i).getTemp());
                System.out.printf("%3c", '|');
                System.out.println();
            }
        } else {
            System.out.println("Brak gwiazd w bazie! Dodaj gwiazdy aby wyświetlić listę.");
            System.out.println();
        }
        System.out.println();
    }

    // dodanie gwiazdy do bazy
    public static String dodajGwiazde(ArrayList<Gwiazda> lista) {

        Scanner pobierzDane = new Scanner(System.in);
        boolean valid = false;
        String nazwa = null, gwiazdozbior, polkula = null, deklinacja = null, rektascensja = null;
        double obsMagnitudo = -50, odleglosc = 0, masa = 0, temp = 0;

        System.out.println("Podaj nazwę gwiazdy:");

        if (lista.size() != 0) {
            boolean czyIstnieje = false;
            String n1 = "";
            while (!n1.equals(nazwa)) {
                n1 = pobierzDane.nextLine();
                for (Gwiazda g : lista) {
                    if (g.getName().equals(n1)) {
                        czyIstnieje = true;
                    }
                }
                if (czyIstnieje) {
                    System.out.println("Gwiazda o podanej nazwie już istnieje, podaj inną nazwę.");
                    czyIstnieje = false;
                } else {
                    nazwa = n1;
                }
            }
        }

        System.out.println();

        System.out.println("Podaj gwiazdozbiór:");
        gwiazdozbior = pobierzDane.nextLine();
        System.out.println();

        System.out.println("Podaj półkulę (PN/PD):");
        while (polkula == null) {
            String pp = pobierzDane.nextLine();
            if (pp.equals("PN") || pp.equals("PD")) {
                polkula = pp;
            } else {
                System.out.println("Dopuszczalne są jedynie wartości PN (północna) i PD (południowa). Spróbuj ponownie.");
            }
        }
        System.out.println();

        System.out.println("Podaj deklinację:");
        int stopnie = -1000, minuty = -1000;
        double sekundy = -100;
        if (polkula.equals("PN")) {
            System.out.println("Podaj stopnie z zakresu 0-90.");
            while (stopnie < 0 || stopnie > 90) {
                try {
                    stopnie = pobierzDane.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Musisz podać liczbę całkowitą z podanego zakresu!");
                    pobierzDane.nextLine();
                }
                if (stopnie >= 0 && stopnie <= 90) {
                    deklinacja = String.valueOf(stopnie);
                    deklinacja += '\u00b0';
                } else {
                    System.out.println("Niewłaściwy zakres! Podaj stopnie z zakresu 0-90!");
                }
            }
        } else if (polkula.equals("PD")) {
            System.out.println("Podaj stopnie z zakresu -90-0.");
            while (stopnie < -90 || stopnie > 0) {
                try {
                    stopnie = pobierzDane.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Musisz podać liczbę całkowitą z podanego zakresu!");
                    pobierzDane.nextLine();
                }
                if (stopnie >= -90 && stopnie <= 0) {
                    deklinacja = String.valueOf(stopnie);
                    deklinacja += '\u00b0';
                } else {
                    System.out.println("Podaj stopnie z zakresu -90-0!");
                }
            }
        }

        if (stopnie == -90 || stopnie == 90) {
            minuty = 0;
            sekundy = 0;
        } else {
            System.out.println("Podaj minuty z zakresu 0-59.");
            while (minuty < 0 || minuty > 59) {
                try {
                    minuty = pobierzDane.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Musisz podać liczbę całkowitą z podanego zakresu!");
                    pobierzDane.nextLine();
                }
                if (minuty >= 0 && minuty <= 59) {
                    deklinacja += String.valueOf(minuty);
                    deklinacja += "'";
                } else {
                    System.out.println("Niewłaściwy zakres danych! Podaj minuty z zakresu 0-59!");
                }
            }
            System.out.println("Podaj sekundy z zakresu 0-59.99");
            while (sekundy < 0 || sekundy > 59.99) {
                try {
                    String sek1 = pobierzDane.next();
                    String sek2 = sek1.replace(',', '.');
                    sekundy = Double.parseDouble(sek2);
                } catch (NumberFormatException e) {
                    System.out.println("Podaj liczbę rzeczywistą z podanego zakresu!");
                    pobierzDane.nextLine();
                }
                if (sekundy >= 0 && sekundy <= 59.99) {
                    deklinacja += String.valueOf(sekundy);
                    deklinacja += '"';
                } else {
                    System.out.println("Niewłaściwy zakres danych! Podaj sekundy z zakresu 0-59.99!");
                }
            }
        }
        System.out.println();
        System.out.println(deklinacja);
        System.out.println();

        /////

        System.out.println("Podaj rektascensję:");
        int godziny = -1000, minuty2 = -1000;
        double sekundy2 = -100;
        System.out.println("Podaj godziny z zakresu 0-24.");
        while (godziny < 0 || godziny > 24) {
            try {
                godziny = pobierzDane.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Musisz podać liczbę całkowitą z podanego zakresu!");
                pobierzDane.nextLine();
            }
            if (godziny >= 0 && godziny <= 24) {
                rektascensja = String.valueOf(godziny);
                rektascensja += 'h';
            } else {
                System.out.println("Niewłaściwy zakres! Podaj godziny z zakresu 0-24!");
            }
        }

        if (godziny == 24) {
            minuty2 = 0;
            sekundy2 = 0;
        } else {
            System.out.println("Podaj minuty z zakresu 0-59:");
            while (minuty2 < 0 || minuty2 > 59) {
                try {
                    minuty2 = pobierzDane.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Musisz podać liczbę całkowitą z podanego zakresu!");
                    pobierzDane.nextLine();
                }
                if (minuty2 >= 0 && minuty2 <= 59) {
                    rektascensja += String.valueOf(minuty2);
                    rektascensja += "m";
                } else {
                    System.out.println("Niewłaściwy zakres danych! Podaj minuty z zakresu 0-59!");
                }
            }
            System.out.println("Podaj sekundy z zakresu 0-59.99");
            while (sekundy2 < 0 || sekundy2 > 59.99) {
                try {
                    String sek1 = pobierzDane.next();
                    String sek2 = sek1.replace(',', '.');
                    sekundy2 = Double.parseDouble(sek2);
                } catch (NumberFormatException e) {
                    System.out.println("Podaj liczbę rzeczywistą z podanego zakresu!");
                    pobierzDane.nextLine();
                }
                if (sekundy2 >= 0 && sekundy2 <= 59.99) {
                    rektascensja += String.valueOf(sekundy2);
                    rektascensja += 's';
                } else {
                    System.out.println("Niewłaściwy zakres danych! Podaj sekundy z zakresu 0-59.99!");
                }
            }
        }
        System.out.println();
        System.out.println(rektascensja);
        System.out.println();

        System.out.println("Podaj obserwowalną wielkość gwiazdową z zakresu -26.74 - 15.00:");
        double oM = -50;
        while (oM < -26.74 || oM > 15.00) {
            try {
                String oM1 = pobierzDane.next();
                String oM2 = oM1.replace(',', '.');
                oM = Double.parseDouble(oM2);
            } catch (NumberFormatException e) {
                System.out.println("Podaj liczbę rzeczywistą z podanego zakresu!");
                pobierzDane.nextLine();
            }
            if (oM >= -26.74 && oM <= 15.00) {
                obsMagnitudo = oM;
            } else {
                System.out.println("Niewłaściwy zakres danych! Podaj magnitudo z zakresu -26.74 - 15.00!");
            }
        }
        System.out.println();

        System.out.println("Podaj odległość od ziemi w latach świetlnych:");
        double dsT = 0;
        while (dsT < 0.1) {
            try {
                String dsT1 = pobierzDane.next();
                String dsT2 = dsT1.replace(',', '.');
                dsT = Double.parseDouble(dsT2);
            } catch (NumberFormatException e) {
                System.out.println("Podaj liczbę rzeczywistą z podanego zakresu!");
                pobierzDane.nextLine();
            }
            if (dsT > 0) {
                odleglosc = dsT;
            } else {
                System.out.println("Niewłaściwy zakres danych! Odległość nie może być zerowa lub ujemna!");
            }
        }
        System.out.println();

        System.out.println("Podaj masę w masach słońca z zakresu 0.1-50:");
        double mS = 0;
        while (mS < 0.1 || mS > 50) {
            try {
                String ms1 = pobierzDane.next();
                String ms2 = ms1.replace(',', '.');
                mS = Double.parseDouble(ms2);
            } catch (NumberFormatException e) {
                System.out.println("Podaj liczbę rzeczywistą z podanego zakresu!");
                pobierzDane.nextLine();
            }
            if (mS >= 0.1 && mS <= 50) {
                masa = mS;
            } else {
                System.out.println("Niewłaściwy zakres danych! Masa powinna być z zakresu 0.1-50!");
            }
        }
        System.out.println();

        System.out.println("Podaj temperaturę w stopniach Celsjusza:");
        double tmP = 0;
        while (tmP < 2000) {
            try {
                String tmP1 = pobierzDane.next();
                String tmP2 = tmP1.replace(',', '.');
                tmP = Double.parseDouble(tmP2);
            } catch (NumberFormatException e) {
                System.out.println("Podaj liczbę rzeczywistą z podanego zakresu!");
                pobierzDane.nextLine();
            }
            if (tmP >= 2000) {
                temp = tmP;
            } else {
                System.out.println("Niewłaściwy zakres danych! Temperatura nie może być mniejsza od 2000C!");
            }
        }

        String nazwaKatalogowa = null;
        if (lista.size() != 0) {
            int i = 0;
            for (Gwiazda g : lista) {
                if (g.getConstel().equals(gwiazdozbior)) {
                    i++;
                }
            }
            nazwaKatalogowa = greckieLitery[i] + " " + gwiazdozbior;
        } else {
            nazwaKatalogowa = greckieLitery[0] + " " + gwiazdozbior;
        }

        lista.add(new Gwiazda(nazwa, nazwaKatalogowa, gwiazdozbior, polkula, deklinacja, rektascensja, obsMagnitudo, odleglosc, temp, masa));

        return "Pomyślnie dodano gwiazdę do katalogu";
    }

    // usunięcie gwiazdy z bazy
    public static void usunGwiazde(ArrayList<Gwiazda> lista) {
        if (lista.size() != 0) {
            System.out.println("Podaj nazwę gwiazdy:");
            Scanner podaj = new Scanner(System.in);

            String nazwa = podaj.nextLine();
            String constel = null;
            int starQuant = 0;
            boolean czyIstnieje = false;
            try {
                for (Gwiazda g : lista) {
                    if (g.getName().equals(nazwa)) {
                        constel = g.getConstel();
                        lista.remove(g);
                        czyIstnieje = true;
                    }
                }
            } catch (ConcurrentModificationException ignored) {
            }

            if (!czyIstnieje) {
                System.out.println("Gwiazda o podanej nazwie nie istnieje, spróbuj ponownie z inną nazwą.");
            }

            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getConstel().equals(constel)) {
                    lista.get(i).setCatName(greckieLitery[starQuant] + " " + constel);
                    starQuant++;
                }
            }
            System.out.println("Usunięto gwiazdę o nazwie " + nazwa);
        } else {
            System.out.print("Katalog jest pusty!");
        }

        System.out.println();
        System.out.print("Wciśnij klawisz ENTER aby kontynuować...");
        try {
            System.in.read();
        } catch (Exception e) {

        }
    }

    // nazwanie gwiazd po wczytaniu do pliku
    public static void nazwijGwiazdy(ArrayList<Gwiazda> lista){
        String gwiazdozbior = lista.get(0).getConstel();
        int starQuant = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getConstel().equals(gwiazdozbior)) {
                lista.get(i).setCatName(greckieLitery[starQuant] + " " + gwiazdozbior);
                starQuant++;
            }
            else{
                gwiazdozbior = lista.get(i).getConstel();
                starQuant = 0;
                lista.get(i).setCatName(greckieLitery[starQuant] + " " + gwiazdozbior);
                starQuant++;
            }
        }
    }

    // menu wyszukiwania gwiazd
    public static void wyszukiwanie(ArrayList<Gwiazda> lista) {
        Scanner odczyt = new Scanner(System.in);
        System.out.println("Wybierz kryteria wyszukiwania:");
        System.out.println();
        int wybor = 0;
        while (wybor != 7) {
            System.out.println("Menu wyszukiwania:");
            System.out.printf("%39s %n", "1. Wyszukiwanie po gwiazdozbiorze.");
            System.out.printf("%47s %n", "2. Wyszukiwanie po odległości w parsekach.");
            System.out.printf("%44s %n", "3. Wyszukiwanie po zakresie temperatur.");
            System.out.printf("%57s %n", "4. Wyszukiwanie po zakresie obserwowalnych wielkości");
            System.out.printf("%32s %n", "5. Wyszukiwanie po półkuli.");
            System.out.printf("%50s %n", "6. Wyszukiwanie po potencjalnych supernowych.");
            System.out.printf("%33s %n", "7. Wyjście do menu głównego.");
            System.out.println();
            System.out.println("Wpisz cyfrę od 1-7:");

            while (wybor == 0) {
                String wartosc = odczyt.next();
                int a = 0;
                try {
                    a = Integer.parseInt(wartosc);
                    if (a >= 1 && a <= 7) {
                        wybor = a;
                    } else {
                        System.out.println("Podano niewłaściwy zakres! Podaj cyfrę z zakresu 1-6.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Podano niewłaściwy format danych! Podaj cyfrę!");
                }
            }

            switch (wybor) {
                case 1:
                    wyszukiwanieZbior(lista);
                    break;
                case 2:
                    wyszukiwanieOdleglosc(lista);
                    break;
                case 3:
                    wyszukiwanieTemp(lista);
                    break;
                case 4:
                    wyszukiwanieMagni(lista);
                    break;
                case 5:
                    wyszukiwaniePolkula(lista);
                    break;
                case 6:
                    wyszukiwanieSupernova(lista);
                    break;
                case 7:
                    break;
            }
            break;
        }
    }

    // wyszukiwanie po gwiazdozbiorze
    public static void wyszukiwanieZbior(ArrayList<Gwiazda> lista) {
        Scanner odczyt = new Scanner(System.in);
        ArrayList<Gwiazda> znalezione = new ArrayList<>();
        System.out.println("Podaj nazwę gwiazdozbioru:");
        System.out.println();
        String input;
        input = odczyt.nextLine();
        for (Gwiazda g : lista) {
            if (g.getConstel().equals(input)) {
                znalezione.add(g);
            }
        }
        if (znalezione.size() != 0) {
            wyswietlGwiazdy(znalezione);
        } else {
            System.out.println("Nie znaleziono gwiazd w podanym gwiazdozbiorze.");
            System.out.println();
        }
        System.out.print("Wciśnij klawisz ENTER aby kontynuować...");
        try {
            System.in.read();
        } catch (Exception e) {

        }

    }

    // wyszukiwanie po odległości
    public static void wyszukiwanieOdleglosc(ArrayList<Gwiazda> lista) {
        Scanner odczyt = new Scanner(System.in);
        ArrayList<Gwiazda> znalezione = new ArrayList<>();
        System.out.println("Podaj żądany zakres odległości w parsekach:");
        System.out.println();
        double odleglosc1 = -50;
        double odleglosc2 = -50;

        System.out.println("Minimum:");
        while (odleglosc1 <= 0) {
            double odl = -50;
            try {
                String odl1 = odczyt.next();
                String odl2 = odl1.replace(',', '.');
                odl = Double.parseDouble(odl2);
            } catch (NumberFormatException e) {
                System.out.println("Podaj liczbę rzeczywistą wiekszą od 0!");
                odczyt.nextLine();
            }
            if (odl > 0) {
                odleglosc1 = odl;
            } else {
                System.out.println("Podaj liczbę wiekszą niż 0!");
            }
        }

        System.out.println();
        System.out.println("Maximum:");
        while (odleglosc2 <= 0) {
            double odl = -50;
            try {
                String odl1 = odczyt.next();
                String odl2 = odl1.replace(',', '.');
                odl = Double.parseDouble(odl2);
            } catch (NumberFormatException e) {
                System.out.println("Podaj liczbę rzeczywistą wiekszą od 0!");
                odczyt.nextLine();
            }
            if (odl > 0) {
                odleglosc2 = odl;
            } else {
                System.out.println("Podaj liczbę wiekszą niż 0!");
            }
        }

        for (Gwiazda g : lista) {
            if ((g.getDist() / 3.26) <= odleglosc2 && (g.getDist() / 3.26) >= odleglosc1) {
                znalezione.add(g);
            }
        }
        if (znalezione.size() != 0) {
            wyswietlGwiazdy(znalezione);
        } else {
            System.out.println("Nie znaleziono gwiazd w podanym zakresie odległości.");
            System.out.println();
        }
        System.out.print("Wciśnij klawisz ENTER aby kontynuować...");
        try {
            System.in.read();
        } catch (Exception ignored) {
        }

    }

    // wyszukiwanie po temperaturze
    public static void wyszukiwanieTemp(ArrayList<Gwiazda> lista) {
        Scanner odczyt = new Scanner(System.in);
        ArrayList<Gwiazda> znalezione = new ArrayList<>();
        System.out.println("Podaj żądany zakres temperatur:");
        System.out.println();
        double temperatura1 = 0;
        double temperatura2 = 0;

        System.out.println("Minimum:");
        while (temperatura1 < 2000) {
            double tmp = 0;
            try {
                String tmp1 = odczyt.next();
                String tmp2 = tmp1.replace(',', '.');
                tmp = Double.parseDouble(tmp2);
            } catch (NumberFormatException e) {
                System.out.println("Podaj liczbę rzeczywistą wiekszą od 2000!");
                odczyt.nextLine();
            }
            if (tmp >= 2000) {
                temperatura1 = tmp;
            } else {
                System.out.println("Podaj liczbę wiekszą niż 2000!");
            }
        }

        System.out.println();
        System.out.println("Maximum:");
        while (temperatura2 < 2000) {
            double tmp = 0;
            try {
                String tmp1 = odczyt.next();
                String tmp2 = tmp1.replace(',', '.');
                tmp = Double.parseDouble(tmp2);
            } catch (NumberFormatException e) {
                System.out.println("Podaj liczbę rzeczywistą wiekszą od 2000!");
                odczyt.nextLine();
            }
            if (tmp >= 2000) {
                temperatura2 = tmp;
            } else {
                System.out.println("Podaj liczbę wiekszą niż 2000!");
            }
        }

        for (Gwiazda g : lista) {
            if (g.getTemp() >= temperatura1 && g.getTemp() <= temperatura2) {
                znalezione.add(g);
            }
        }
        if (znalezione.size() != 0) {
            wyswietlGwiazdy(znalezione);
        } else {
            System.out.println("Nie znaleziono gwiazd w podanym zakresie temperatur.");
            System.out.println();
        }
        System.out.print("Wciśnij klawisz ENTER aby kontynuować...");
        try {
            System.in.read();
        } catch (Exception e) {

        }

    }

    // wyszukiwaie po wielkości gwiazdowej
    public static void wyszukiwanieMagni(ArrayList<Gwiazda> lista) {
        Scanner odczyt = new Scanner(System.in);
        ArrayList<Gwiazda> znalezione = new ArrayList<>();
        System.out.println("Podaj żądany zakres obserwowanych wielkości gwiazdowych:");
        System.out.println();
        double magnitudo1 = -50;
        double magnitudo2 = -50;

        System.out.println("Minimum:");
        while (magnitudo1 < -26.74 || magnitudo1 > 15.00) {
            double mgn = -50;
            try {
                String mgn1 = odczyt.next();
                String mgn2 = mgn1.replace(',', '.');
                mgn = Double.parseDouble(mgn2);
            } catch (NumberFormatException e) {
                System.out.println("Podaj liczbę z zakresu -26.74 - 15.00!");
                odczyt.nextLine();
            }
            if (mgn >= -26.74 && mgn <= 15.00) {
                magnitudo1 = mgn;
            } else {
                System.out.println("Podaj liczbę z zakresu -26.74 - 15.00!");
            }
        }

        System.out.println();
        System.out.println("Maximum:");
        while (magnitudo2 < -26.74 || magnitudo2 > 15.00) {
            double mgn = -50;
            try {
                String mgn1 = odczyt.next();
                String mgn2 = mgn1.replace(',', '.');
                mgn = Double.parseDouble(mgn2);
            } catch (NumberFormatException e) {
                System.out.println("Podaj liczbę z zakresu -26.74 - 15.00!");
                odczyt.nextLine();
            }
            if (mgn >= -26.74 && mgn <= 15.00) {
                magnitudo2 = mgn;
            } else {
                System.out.println("Podaj liczbę z zakresu -26.74 - 15.00!");
            }
        }

        for (Gwiazda g : lista) {
            if (g.getObsMagnitudo() >= magnitudo1 && g.getObsMagnitudo() <= magnitudo2) {
                znalezione.add(g);
            }
        }
        if (znalezione.size() != 0) {
            wyswietlGwiazdy(znalezione);
        } else {
            System.out.println("Nie znaleziono gwiazd w podanym zakresie magnitudo.");
            System.out.println();
        }
        System.out.print("Wciśnij klawisz ENTER aby kontynuować...");
        try {
            System.in.read();
        } catch (Exception e) {

        }

    }

    // wyszukiwanie po półkuli
    public static void wyszukiwaniePolkula(ArrayList<Gwiazda> lista) {
        Scanner odczyt = new Scanner(System.in);
        ArrayList<Gwiazda> znalezione = new ArrayList<>();
        System.out.println("Podaj półkulę (PN/PD):");
        String polkula = null;
        while (polkula == null) {
            String pp = odczyt.nextLine();
            if (pp.equals("PN") || pp.equals("PD")) {
                polkula = pp;
            } else {
                System.out.println("Dopuszczalne są jedynie wartości PN (północna) i PD (południowa). Spróbuj ponownie.");
            }
        }
        System.out.println();

        for (Gwiazda g : lista) {
            if (g.getHemisph().equals(polkula)) {
                znalezione.add(g);
            }
        }
        if (znalezione.size() != 0) {
            wyswietlGwiazdy(znalezione);
        } else {
            System.out.println("Nie znaleziono gwiazd na podanej półkuli.");
            System.out.println();
        }
        System.out.print("Wciśnij klawisz ENTER aby kontynuować...");
        try {
            System.in.read();
        } catch (Exception e) {

        }

    }

    // wyszukiwanie supernowych
    public static void wyszukiwanieSupernova(ArrayList<Gwiazda> lista) {
        ArrayList<Gwiazda> znalezione = new ArrayList<>();
        System.out.println();
        for (Gwiazda g : lista) {
            if (g.getMass() > 1.44) {
                znalezione.add(g);
            }
        }
        if (znalezione.size() != 0) {
            System.out.println("Wyświetlam potencjalne supernowe:");
            wyswietlGwiazdy(znalezione);
        } else {
            System.out.println("Nie znaleziono potencjalnych supernowych.");
            System.out.println();
        }
        System.out.print("Wciśnij klawisz ENTER aby kontynuować...");
        try {
            System.in.read();
        } catch (Exception e) {

        }

    }

    public static String zapisDanych(ArrayList<Gwiazda> lista, String path) {
        try {
            FileOutputStream nowyPlik = new FileOutputStream(new File(path));

            ObjectOutputStream doPliku = new ObjectOutputStream(nowyPlik);

            for (Gwiazda g : lista) {
                doPliku.writeObject(g);
            }

            doPliku.close();
            nowyPlik.close();

            System.out.println("Zapis tablicy do pliku pomyślny.");
            System.out.println();
        } catch (FileNotFoundException a) {
            System.out.println("Nie znaleziono pliku.");
        } catch (IOException b) {
            System.out.println("Błąd odczytu.");
        }

        return "Zapisano dane.";
    }

    public static String odczytDanych(ArrayList<Gwiazda> lista, String path) {
        boolean dalej = true;

        try{
            FileInputStream OdczytPliku = new FileInputStream(path);

            ObjectInputStream zPliku = new ObjectInputStream(OdczytPliku);

            while(dalej){
                Object obj = null;

                try {
                    obj = zPliku.readObject();
                } catch (ClassNotFoundException e) {}

                if (obj != null) {
                    Gwiazda gw = (Gwiazda) obj;
                    if(lista.size() != 0){
                        try{
                            for(Gwiazda g : lista){
                                if(g.getName().equals(gw.getName())){
                                    System.out.println("Gwiazda już istnieje!");
                                }
                                else{
                                    lista.add(gw);
                                }
                            }
                        }catch(ConcurrentModificationException ignored){

                        }
                    }
                    else{
                        lista.add(gw);
                    }

                } else {
                    dalej = false;
                }

                nazwijGwiazdy(lista);
            }

            OdczytPliku.close();
            zPliku.close();
        }
        catch (FileNotFoundException a){
            System.out.println("Nie znaleziono pliku.");
        }
        catch (IOException b){

        }

        return "Odczyt pomyślny.";
    }

    public static void main(String[] args) {
        Scanner odczyt = new Scanner(System.in);

        System.out.println("Witaj w katalogu gwiazd! Wybierz opcję z menu głównego:");
        System.out.println();
        int wybor = 0;
        while (wybor != 7) {
            System.out.println("Menu główne:");
            System.out.printf("%32s %n", "1. Wyświetl katalog gwiazd.");
            System.out.printf("%22s %n", "2. Dodaj Gwiazdę.");
            System.out.printf("%21s %n", "3. Usuń gwiazdę.");
            System.out.printf("%23s %n", "4. Wyszukiwanie...");
            System.out.printf("%32s %n", "5. Wczytaj gwiazdy z pliku.");
            System.out.printf("%24s %n", "6. Zapisz do pliku.");
            System.out.printf("%16s %n", "7. Wyjście.");
            System.out.println();
            System.out.println("Wpisz cyfrę od 1-7:");

            while (wybor == 0) {
                String wartosc = odczyt.next();
                int a = 0;
                try {
                    a = Integer.parseInt(wartosc);
                    if (a >= 1 && a <= 7) {
                        wybor = a;
                    } else {
                        System.out.println("Podano niewłaściwy zakres! Podaj cyfrę z zakresu 1-6.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Podano niewłaściwy format danych! Podaj cyfrę!");
                }
            }

            switch (wybor) {
                case 1:
                    wyswietlGwiazdy(katalogGwiazd);
                    System.out.println();
                    wybor = 0;
                    break;
                case 2:
                    System.out.println(dodajGwiazde(katalogGwiazd));
                    System.out.println();
                    System.out.print("Wciśnij klawisz ENTER aby kontynuować...");
                    try{
                        System.in.read();
                    }catch (Exception e){

                    }
                    wybor = 0;
                    break;
                case 3:
                    usunGwiazde(katalogGwiazd);
                    System.out.println();
                    wybor = 0;
                    break;
                case 4:
                    wyszukiwanie(katalogGwiazd);
                    System.out.println();
                    wybor = 0;
                    break;
                case 5:
                    System.out.println(odczytDanych(katalogGwiazd, "Gwiazdy.obj"));
                    wybor = 0;
                    break;
                case 6:
                    System.out.println(zapisDanych(katalogGwiazd, "Gwiazdy.obj"));
                    wybor = 0;
                    break;
                case 7:
                    System.exit(0);
            }
        }
    }
}

