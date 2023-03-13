package Utils;

import lombok.Data;
import org.dto.PrivatService;
import org.functionalInteface.BanksUtil;

import java.text.DecimalFormat;

@Data
public class UserPreferences {
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
        time = "what?";
    }
}
