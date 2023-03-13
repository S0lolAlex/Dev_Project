package Utils;

import org.dto.PrivatService;
import org.functionalInteface.BanksUtil;
import java.text.DecimalFormat;

public class
UserPreferences {
    private BanksUtil bank;
    private DecimalFormat df;
    private boolean isUsd;
    private boolean isEur;
    private boolean isOne;
    private String currency;
    private String time;

    public UserPreferences() {
        bank = new PrivatService();
        df = new DecimalFormat();
        isUsd = false;
        isEur = false;
        isOne = true;
        currency = "USD";
        time = "вимкнено";
    }

    public BanksUtil getBank() {
        return bank;
    }

    public void setBANK(BanksUtil BANK) {
        this.bank = BANK;
    }

    public DecimalFormat getDf() {
        return df;
    }

    public void setDf(DecimalFormat df) {
        this.df = df;
    }

    public boolean isUsd() {
        return isUsd;
    }

    public void setUsd(boolean usd) {
        this.isUsd = usd;
    }

    public boolean isEur() {
        return isEur;
    }

    public void setEur(boolean eur) {
        this.isEur = eur;
    }

    public boolean isOne() {
        return isOne;
    }

    public void setOne(boolean one) {
        this.isOne = one;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
