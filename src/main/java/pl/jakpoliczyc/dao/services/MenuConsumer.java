package pl.jakpoliczyc.dao.services;

import pl.jakpoliczyc.dao.entities.Menu;

import java.util.function.Consumer;

@FunctionalInterface
public interface MenuConsumer extends Consumer<Menu> {
}
