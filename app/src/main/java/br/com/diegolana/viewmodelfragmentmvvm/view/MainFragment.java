package br.com.diegolana.viewmodelfragmentmvvm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.diegolana.viewmodelfragmentmvvm.R;
import br.com.diegolana.viewmodelfragmentmvvm.viewModel.MainViewModel;
import br.com.diegolana.viewmodelfragmentmvvm.MainFragmentBinding;
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


        mViewModel = ViewModelProviders.of(this.getActivity()).get(MainViewModel.class);
        MainFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        binding.setMainViewModel(mViewModel);

        setupDataBinding();

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel.userLiveData.observe(this.getActivity(), new Observer<User>() {
              @Override
              public void onChanged(@Nullable User data) {
                  if (data != null) {
                      TextView text = getView().findViewById(R.id.message);
                      text.setText(data.getFistName());
                  }
              }
          });

        getView().findViewById(R.id.button_bk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.doBackgroundAction();
            }
        });

        getView().findViewById(R.id.button_ui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.doUIAction();
            }
        });

    }


    private void setupDataBinding() {

//        ActivityUserProfileBinding activityUserProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile);
//        UserProfileModel userProfileModel = new UserProfileModel("Emily Lin", ContextCompat.getDrawable(this, R.mipmap.ic_launcher), 26);
//        activityUserProfileBinding.setModel(userProfileModel);
//        activityUserProfileBinding.setViewModel(new UserProfileViewModel(this));
    }

}
