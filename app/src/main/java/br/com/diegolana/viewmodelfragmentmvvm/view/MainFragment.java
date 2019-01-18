package br.com.diegolana.viewmodelfragmentmvvm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.diegolana.viewmodelfragmentmvvm.R;
import br.com.diegolana.viewmodelfragmentmvvm.viewModel.MainViewModel;
import br.com.diegolana.viewmodelfragmentmvvm.model.User;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
        mViewModel.userLiveData.observe(this, new Observer<User>() {
              @Override
              public void onChanged(@Nullable User data) {
                  TextView text = getView().findViewById(R.id.message);
                  text.setText(data.getFistName());
              }
          });

        getView().findViewById(R.id.button_bk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.doBackgoundAction();
            }
        });

        getView().findViewById(R.id.button_ui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.doUIAction();
            }
        });

    }

}
