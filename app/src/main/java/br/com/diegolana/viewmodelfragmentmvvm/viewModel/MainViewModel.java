package br.com.diegolana.viewmodelfragmentmvvm.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import br.com.diegolana.viewmodelfragmentmvvm.model.User;
import br.com.diegolana.viewmodelfragmentmvvm.provider.Provider;

public class MainViewModel extends ViewModel {
    private Provider provider;
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();

    public MainViewModel() {
        provider = new Provider();
        User user = loadUser(false);
        userLiveData.setValue(user);
    }

    private Provider.UserCallBack userCallBack = new Provider.UserCallBack() {
        @Override
        public void receiveUser(User user) {
            userLiveData.postValue(user);
        }
    };

    public void doBackgroundAction() {
        userLiveData.setValue(null);
        provider.getUser(true, userCallBack);
    }

    public void doUIAction() {
        userLiveData.setValue(null);

        User user = loadUser(true);
        userLiveData.setValue(user);
    }

    private User loadUser(boolean sleep) {
        return provider.getUser(sleep);
    }

    public void onClickedVM() {
        Log.d("TAG","Clicked!!!");
    }

    public MutableLiveData<User> getUserLiveData() {
        return userLiveData;
    }
}
