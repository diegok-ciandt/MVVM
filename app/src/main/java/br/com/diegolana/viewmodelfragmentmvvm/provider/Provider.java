package br.com.diegolana.viewmodelfragmentmvvm.provider;

import android.os.Handler;

import java.util.Random;

import br.com.diegolana.viewmodelfragmentmvvm.model.User;

public class Provider {

    private static final int ONE_SECOND = 1000;

    Handler mHandler = new Handler();

    public User getUser(boolean sleep) {
        User user = new User();
        user.setFistName(randText());

        if (sleep) {
            sleep(ONE_SECOND);
        }
        return user;
    }

    public void getUser(boolean sleep, UserCallBack userCallBack) {
        runInBackground(sleep,userCallBack);
    }

    private void runInBackground(final boolean sleep, final UserCallBack userCallBack) {

        new Thread(new Runnable() {
            @Override
            public void run () {
                final User user = getUser(sleep);
                mHandler.post(new Runnable() {
                    @Override
                    public void run () {
                        userCallBack.receiveUser(user);
                    }
                });
            }
        }).start();
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            //handle
        }
    }

    private String randText() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(100);

        return ""+randomLength;
    }


    public interface UserCallBack {
        void receiveUser(User user);
    }
}
