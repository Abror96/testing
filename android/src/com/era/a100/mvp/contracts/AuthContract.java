package com.era.a100.mvp.contracts;

public interface AuthContract {

    interface View {

        void onAuthSuccess(String token, String refreshToken);

        void showSnackbar(String message);

        void showProgress();

        void hideProgress();

    }

    interface Presenter {

        void onDestroy();

        void onAuthClicked(String email, String password);

    }

    interface Interactor {

        interface OnFinishedListener {

            void onFinished(String token, String refreshToken);

            void onFailure(String message);

        }

        void auth(OnFinishedListener onFinishedListener, String email, String password);
    }

}
