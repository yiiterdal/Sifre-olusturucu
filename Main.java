import java.util.Scanner;
import java.util.Random;

public class GelismisSifreUretici {

    private static final String KUCUK_HARFLER = "abcdefghijklmnopqrstuvwxyz";
    private static final String BUYUK_HARFLER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String RAKAMLAR = "0123456789";
    private static final String OZEL_KARAKTERLER = "!@#$%^&*()_+-=[]{}|;:,.<>?";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════╗");
        System.out.println("║    GELİŞMİŞ ŞİFRE ÜRETİCİ    ║");
        System.out.println("╚══════════════════════════════╝");

        int uzunluk = sifreUzunluguAl(scanner);
        boolean kucukHarfKullan = karakterSecenek(scanner, "Küçük harf");
        boolean buyukHarfKullan = karakterSecenek(scanner, "Büyük harf");
        boolean rakamKullan = karakterSecenek(scanner, "Rakam");
        boolean ozelKarakterKullan = karakterSecenek(scanner, "Özel karakter");

        String karakterSeti = karakterSetiOlustur(kucukHarfKullan, buyukHarfKullan,
                rakamKullan, ozelKarakterKullan);

        if (karakterSeti.isEmpty()) {
            System.out.println("Hata: En az bir karakter türü seçmelisiniz!");
            return;
        }

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║      ÜRETİLEN ŞİFRELER       ║");
        System.out.println("╚══════════════════════════════╝");

        for (int i = 0; i < 5; i++) {
            String sifre = sifreUret(karakterSeti, uzunluk);
            System.out.println((i+1) + ". Şifre: " + sifre);
        }

        System.out.println("\nNot: Şifrelerinizi güvenli bir yerde saklayın!");
    }

    private static int sifreUzunluguAl(Scanner scanner) {
        int uzunluk = 0;
        while (uzunluk < 8 || uzunluk > 32) {
            System.out.print("Şifre uzunluğu (8-32): ");
            try {
                uzunluk = Integer.parseInt(scanner.nextLine());
                if (uzunluk < 8 || uzunluk > 32) {
                    System.out.println("Uzunluk 8 ile 32 arasında olmalıdır!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz giriş! Sayı giriniz.");
            }
        }
        return uzunluk;
    }

    private static boolean karakterSecenek(Scanner scanner, String karakterTuru) {
        System.out.print(karakterTuru + " kullanılsın mı? (e/h): ");
        String cevap = scanner.nextLine().toLowerCase();
        return cevap.equals("e") || cevap.equals("evet");
    }

    private static String karakterSetiOlustur(boolean kucukHarf, boolean buyukHarf,
                                              boolean rakam, boolean ozelKarakter) {
        StringBuilder karakterSeti = new StringBuilder();

        if (kucukHarf) karakterSeti.append(KUCUK_HARFLER);
        if (buyukHarf) karakterSeti.append(BUYUK_HARFLER);
        if (rakam) karakterSeti.append(RAKAMLAR);
        if (ozelKarakter) karakterSeti.append(OZEL_KARAKTERLER);

        return karakterSeti.toString();
    }

    private static String sifreUret(String karakterSeti, int uzunluk) {
        Random random = new Random();
        StringBuilder sifre = new StringBuilder();

        for (int i = 0; i < uzunluk; i++) {
            int rastgeleIndex = random.nextInt(karakterSeti.length());
            sifre.append(karakterSeti.charAt(rastgeleIndex));
        }

        return sifre.toString();
    }
}