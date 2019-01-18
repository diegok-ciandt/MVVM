package br.com.diegolana.viewmodelfragmentmvvm.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import br.com.diegolana.viewmodelfragmentmvvm.model.User;
import br.com.diegolana.viewmodelfragmentmvvm.provider.Provider;

public class MainViewModel extends ViewModel {
    private Provider provider;
    private User user;

    public final MutableLiveData<User> userLiveData = new MutableLiveData<>();

    public MainViewModel() {
        provider = new Provider();
    }

    public User getUser() {
        if (user == null) {
            user = new MutableLiveData<User>().getValue();
        }
        return user;
    }

    private Provider.UserCallBack userCallBack = new Provider.UserCallBack() {
        @Override
        public void receiveUser(User user) {
            userLiveData.postValue(user);
        }
    };

    public void doBackgroundAction() {
        provider.getUser(true,userCallBack);
    }

    public void doUIAction() {
        userLiveData.setValue(loadUser(true));
    }

    private User loadUser(boolean sleep) {
        return provider.getUser(sleep);
    }

}
