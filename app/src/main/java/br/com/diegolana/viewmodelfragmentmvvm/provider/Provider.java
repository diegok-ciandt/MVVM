package br.com.diegolana.viewmodelfragmentmvvm.provider;

import br.com.diegolana.viewmodelfragmentmvvm.model.User;

public class Provider {

    public User getUser() {
        User user = new User("Diego", "Lana", 36);

        sleep(10000);

        return user;
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            //handle
        }
    }
}
