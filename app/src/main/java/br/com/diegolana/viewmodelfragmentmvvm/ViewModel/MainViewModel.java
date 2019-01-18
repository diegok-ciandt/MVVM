package br.com.diegolana.viewmodelfragmentmvvm.ViewModel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import br.com.diegolana.viewmodelfragmentmvvm.model.User;

public class MainViewModel extends ViewModel {
    public final LiveData<User> userLiveData = new LiveData<User>() {
        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<User> observer) {
            super.observe(owner, observer);
        }
    };

    public MainViewModel() {
        // trigger user load.
    }

    public void doAction() {
        // depending on the action, do necessary business logic calls and update the
        // userLiveData.
    }
}
