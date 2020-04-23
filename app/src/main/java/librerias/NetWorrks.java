package librerias;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class NetWorrks extends AppCompatActivity {
    private Activity _activity;
    public NetWorrks(Activity activity){
        _activity = activity;

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean verificarNetworks(){
        boolean valor = false;
        ConnectivityManager cm = (ConnectivityManager) _activity.getSystemService(Context.CONNECTIVITY_SERVICE);
     if(cm != null){
         NetworkCapabilities nc = null;
         if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
             nc = cm.getNetworkCapabilities(cm.getActiveNetwork());
         }
         if(nc != null){
             if(nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                 valor = true;
             }else if(nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                 valor = true;
             }

         }else {

         }
     }else {
         valor = false;
     }
     return valor;
    }
}
