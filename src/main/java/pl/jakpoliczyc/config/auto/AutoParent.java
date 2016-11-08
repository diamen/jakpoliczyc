package pl.jakpoliczyc.config.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoParent {

    private AutoChild autoChild;

    @Autowired
    public AutoParent(AutoChild autoChild) {
        this.autoChild = autoChild;
    }

    public void run() {
        autoChild.run();
    }

}
