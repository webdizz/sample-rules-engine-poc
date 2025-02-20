package name.webdizz.poc.rules.engine.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Decision {

    private boolean approved;
    private String reason;

    public Decision() {
        this.approved = false;
    }

    public void approve() {
        this.approved = true;
    }

    public void reject(String reason) {
        this.approved = false;
        this.reason = reason;
    }
}
