import java.io.*;
import java.util.*;

public class Kadry implements Serializable
{
    private Pracownik [] pracownicy_;
    private int zatrudnienie_;
    private int maksPracownikow;
    public Kadry()
    {
        this.maksPracownikow=100;
        pracownicy_=new Pracownik[this.maksPracownikow];
    }
    public Kadry(int maksPracownikow)
    {
        this.maksPracownikow=maksPracownikow;
        pracownicy_=new Pracownik[maksPracownikow];

    }
    public void dodajPracownika(Pracownik p)
    {
        zatrudnienie_++;
        try
        {
            for (int i = zatrudnienie_ - 1; i < zatrudnienie_; i++)
            {
                pracownicy_[i] = p;
            }
        }
        catch (ArrayIndexOutOfBoundsException e){}
    }

    public void dodajPracownikaInteraktywnie()
    {
        zatrudnienie_++;
        System.out.println("Podaj dane nowego pracownika:");
        Scanner scanner=new Scanner(System.in);
        System.out.print("Imię: ");
        String imie = scanner.nextLine();
        System.out.print("Nazwisko: ");
        String nazwisko = scanner.nextLine();
        System.out.print("Płeć: ");
        char plec = scanner.next().charAt(0);
        System.out.print("Dział: ");
        int dzial = scanner.nextInt();
        System.out.print("Płaca: ");
        double placa = scanner.nextDouble();
        Pracownik p=new Pracownik(imie,nazwisko,placa,plec,dzial);
        pracownicy_[zatrudnienie_-1]=p;
        pracownicy_[zatrudnienie_-1].setImie(imie);
        pracownicy_[zatrudnienie_-1].setNazwisko(nazwisko);
        pracownicy_[zatrudnienie_-1].setPlec(plec);
        pracownicy_[zatrudnienie_-1].setDzial(dzial);
        pracownicy_[zatrudnienie_-1].setPlaca(placa);

    }

    public void importujZPlikuTekstowego(String nazwaPliku) throws IOException
    {
        File file=new File (nazwaPliku);//odczyt z pliku
        Scanner in = new Scanner(file);
        String zdanie = in.nextLine();
        System.out.println(zdanie);
        while(in.hasNextLine())
         {
              System.out.println(in.nextLine());
         }
    }
    public double sredniZarobek()
    {
        double zarobek=0,srednia;
        Pracownik p;
        for(int i=0;i<zatrudnienie_;i++)
        {
            zarobek+=pracownicy_[i].getPlaca();
        }
        srednia=zarobek/zatrudnienie_;
        srednia=srednia*100;
        srednia=Math.round(srednia);
        srednia=srednia/100;
        return srednia;
    }

    public double sredniZarobek(int dzial)
    {
        double zarobek=0,srednia;
        int zatrudnionychWDziale=0;
        for(int i=0;i<zatrudnienie_;i++)
        {
            if(dzial==pracownicy_[i].getDzial())
            {
                zarobek+=pracownicy_[i].getPlaca();
                zatrudnionychWDziale++;
            }
        }

        srednia=zarobek/zatrudnionychWDziale;
        return srednia;
    }

    public int[] dajDzialy()
    {
        String dzial="";
        int[] dzialy=new int [zatrudnienie_];
        for(int i = 0; i < dzialy.length;i++)
        {
            dzialy[i]=pracownicy_[i].getDzial();
        }

        //dzięki tej pętli zostaną usunięte powtórzenia numerów działów
        for (int i = 0; i < zatrudnienie_-1; i++)
        {
            for (int j = i + 1; j < zatrudnienie_; j++)
            {
                if (dzialy[i] == dzialy[j])
                {
                    for (int k = j; k < dzialy.length- 1; k++)
                    {
                        dzialy[k] = pracownicy_[k + 1].getDzial();
                    }
                    zatrudnienie_--;
                    j--;
                }
            }
        }
        return dzialy;
    }
    public String pisz()
    {
        java.text.DecimalFormat df=new java.text.DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        String wynik="";
        try {
            wynik = "Liczba pracowników: " + zatrudnienie_;
            for (int i = 0; i < zatrudnienie_; i++) {
                wynik = wynik.concat("\n" + pracownicy_[i] + " ");
            }
            wynik = wynik.concat("\nŚrednia płaca w firmie: " + df.format(sredniZarobek()) + " zł.");
            for (int i = 0; i < zatrudnienie_; i++) {
                if (sredniZarobek(i + 1) > 0) {
                    wynik = wynik.concat("\nŚrednia płaca w dziale " + (i + 1) + " wynosi: " +
                            df.format(sredniZarobek(i + 1)) + " zł.");
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            wynik="Dodałeś za dużo pracowników ("+zatrudnienie_+"). Limit to: "+maksPracownikow;
        }
        return wynik;
    }

    public void zapiszDoPliku(String nazwa) throws IOException
    {
        FileWriter plik = new FileWriter(nazwa);
        plik.write(pisz());
        plik.close();
    }
}
