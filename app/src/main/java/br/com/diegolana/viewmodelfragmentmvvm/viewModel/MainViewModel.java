package br.com.diegolana.viewmodelfragmentmvvm.viewModel;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import br.com.diegolana.viewmodelfragmentmvvm.model.User;
import br.com.diegolana.viewmodelfragmentmvvm.provider.Provider;

public class MainViewModel extends ViewModel {
    private Provider provider;
    public String name = "Teste";
    public final MutableLiveData<User> userLiveData = new MutableLiveData<>();
    private User user;
    private Activity activity;

    public User getUser() {
        if (user == null) {
            user = new MutableLiveData<User>().getValue();
        }
        return user;
    }

    public MainViewModel (@NonNull Activity activity) {
        this.activity = activity;
    }

    public MainViewModel() {
        provider = new Provider();
        getUser();
    }

    private Provider.UserCallBack userCallBack = new Provider.UserCallBack() {
        @Override
        public void receiveUser(User user) {
            name = user.getFistName();
            MainViewModel.this.user = user;
            userLiveData.postValue(user);
        }
    };

    public void doBackgroundAction() {
        userLiveData.setValue(null);
        provider.getUser(true,userCallBack);
    }

    public void doUIAction() {
        User user = null;
        userLiveData.setValue(user);

        user = loadUser(true);
        this.user = user;
        name = user.getFistName();
        userLiveData.setValue(user);
    }

    private User loadUser(boolean sleep) {
        return provider.getUser(sleep);
    }

    public void onClickedVM() {
        Log.d("TAG","Clicked!!!");
    }

}
