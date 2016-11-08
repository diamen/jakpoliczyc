package pl.jakpoliczyc;

public class LazyThing {

    public LazyThing() {

        try {
            Class.forName("DOESNT EXISTS");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
