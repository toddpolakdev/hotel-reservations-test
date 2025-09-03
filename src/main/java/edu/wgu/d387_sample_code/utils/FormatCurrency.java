package edu.wgu.d387_sample_code.utils;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public final class FormatCurrency {
    private static final NumberFormat USD = NumberFormat.getCurrencyInstance(Locale.US);
    private static final NumberFormat CAD = NumberFormat.getCurrencyInstance(Locale.CANADA);
    private static final NumberFormat EUR = NumberFormat.getCurrencyInstance(Locale.GERMANY);

    static {
        USD.setCurrency(Currency.getInstance("USD"));
        CAD.setCurrency(Currency.getInstance("CAD"));
        EUR.setCurrency(Currency.getInstance("EUR"));
    }
    public static String usd(double amount) { return USD.format(amount); }
    public static String cad(double amount)  {return CAD.format(amount); }
    public static String eur(double amount) { return EUR.format(amount); }
}
