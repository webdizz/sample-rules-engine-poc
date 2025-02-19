package name.webdizz.poc.rules.engine.domain;

import lombok.Data;

@Data
public class Decision {

    private boolean approved;

    public Decision() {
        this.approved = false;
    }

    public Decision approve() {
        this.approved = true;
        return this;
    }

    public Decision reject() {
        this.approved = false;
        return this;
    }
}
