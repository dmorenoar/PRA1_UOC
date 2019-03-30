package dmorenoar.uoc.pra1_uoc;

import android.app.Application;

public class Aplicacion extends Application {

    private String user;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
