import java.io.IOException;
import java.io.Serializable;

public class Pracownik implements Serializable
{
    private String imie;
    private String nazwisko;
    private double placa;
    private char plec;
    private int dzial;

    public Pracownik(String imie, String nazwisko, double placa, char plec, int dzial)
    {
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.placa=placa;
        this.plec=plec;
        this.dzial=dzial;
    }

    public boolean czyPracujeWDziale(int numerDzialu)
    {
        Pracownik p=new Pracownik(imie,nazwisko,placa,plec,dzial);
        boolean wynik;
        if(p.dzial==numerDzialu){wynik=true;}
        else{wynik=false;}

        return wynik;
    }

    public void setImie(String imie)
    {
        this.imie=imie;
    }
    public void setNazwisko(String nazwisko)
    {
        this.nazwisko=nazwisko;
    }
    public void setPlec(char plec)
    {
        this.plec=plec;
    }
    public void setDzial(int dzial)
    {
        this.dzial=dzial;
    }
    public void setPlaca(double placa)
    {
        this.placa=placa;
    }
    
    public double getPlaca()
    {
        return placa;
    }

    public int getDzial()
    {
        return dzial;
    }

    public String toString ()
    {
        String wynik = "", wynik1, wynik2 = "", wynik3 = "", wynik4 = "", wynik5 = "";
        wynik1 = wynik.concat("|" + imie);
        for (int i = 0; i < 14 - imie.length(); i++) {
            wynik1 = wynik1.concat(" ");
        }
        String cenka = "" + nazwisko;
        String sztuki = "" + placa;
        wynik2 = wynik1.concat("|" + cenka  );
        for (int i = 0; i < 12 - cenka.length(); i++) {
            wynik2 = wynik2.concat(" ");
        }
        wynik3 = wynik2.concat("|" + placa + " zł." );
        for (int i = 0; i < 8 - sztuki.length(); i++) {
            wynik3 = wynik3.concat(" ");
        }
        String wynik6=""+dzial;
        wynik4 = wynik3.concat("|  " + plec );
        for (int i = 0; i < 3 - wynik6.length(); i++) {
            wynik4 = wynik4.concat(" ");
        }
        wynik5 = wynik4.concat("|" + dzial );
        return wynik5;
    }

    public static void main(String[] args) throws IOException
    {
        Pracownik pracownik1=new Pracownik("Damian","Bilski",3600,'m',1);
        Pracownik pracownik2=new Pracownik("Jan","Mazur",4200,'m',2);
        Pracownik pracownik3=new Pracownik("Anna","Nowak",3500,'k',1);
        Pracownik pracownik4=new Pracownik("Janusz","Wach",4700,'m',1);
        Pracownik pracownik5=new Pracownik("Dorota","Szary",3700,'k',2);
//        System.out.println(pracownik1);
//        System.out.println(pracownik1.czyPracujeWDziale(3));
        Kadry k=new Kadry();
        k.dodajPracownika(pracownik1);
        k.dodajPracownika(pracownik3);
        k.dodajPracownika(pracownik2);
        k.dodajPracownika(pracownik4);
        k.dodajPracownika(pracownik5);
//        k.dodajPracownikaInteraktywnie();

        System.out.println(k.pisz());

//        System.out.println(k.dajDzialy());
//        System.out.println("Sredni zarobek w tej kadrze wynosi: "+k.sredniZarobek());
//        int dzial=1;
//        System.out.println("Sredni zarobek w dziale " + dzial + " wynosi: "+k.sredniZarobek(dzial));
//        System.out.println("Działy w jakich pracują pracownicy to odpowiednio: " + k.dajDzialy());
//        k.importujZPlikuTekstowego("kadra.txt");
    }
}



