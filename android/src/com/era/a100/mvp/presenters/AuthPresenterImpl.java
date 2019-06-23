package com.era.a100.mvp.presenters;

import com.era.a100.mvp.contracts.AuthContract;

public class AuthPresenterImpl implements AuthContract.Presenter, AuthContract.Interactor.OnFinishedListener {

    private AuthContract.View view;
    private AuthContract.Interactor interactor;

    public AuthPresenterImpl(AuthContract.View view, AuthContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onAuthClicked(String email, String password) {
        if (view != null) {
            view.showProgress();
        }
        interactor.auth(this, email, password);
    }

    @Override
    public void onFinished(String token, String refreshToken) {
        if (view != null) {
            view.hideProgress();
            view.onAuthSuccess(token, refreshToken);
        }
    }

    @Override
    public void onFailure(String message) {
        if (view != null) {
            view.hideProgress();
            view.showSnackbar(message);
        }
    }
}
