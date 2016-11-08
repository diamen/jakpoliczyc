package pl.jakpoliczyc.config.auto;

import org.springframework.stereotype.Repository;

@Repository
public class AutoChild {

    public void run() {
        System.out.println(this.toString());
    }

}
