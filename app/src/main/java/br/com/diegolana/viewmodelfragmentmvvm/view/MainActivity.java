package br.com.diegolana.viewmodelfragmentmvvm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.diegolana.viewmodelfragmentmvvm.R;
import br.com.diegolana.viewmodelfragmentmvvm.model.User;
import br.com.diegolana.viewmodelfragmentmvvm.viewModel.MainViewModel;
import br.com.diegolana.viewmodelfragmentmvvm.MainActivityBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        binding.setLifecycleOwner(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }

        setupViewModel();
        binding.setMainViewModel(viewModel);
        viewModel.doBackgroundAction();
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        viewModel.getUserLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User data) {
                View progressBar = findViewById(R.id.progress_bar);
                if (data != null) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });

    }


}
