package pl.jakpoliczyc.cache;

import org.springframework.cache.annotation.Cacheable;

public class Cached {

    @Cacheable(value = "test")
    public int getSth(int x) {
        System.out.println("IM NOT CACHED YET");
        return x;
    }

}
