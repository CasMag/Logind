package librerias;

import android.text.TextWatcher;
import android.util.Pair;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;

import com.example.logind.R;

import models.BindableString;

public class BindEditText {
    @BindingAdapter({"app:binding"})
    public static void bindEditText(EditText view, final BindableString bindableString){
        Pair pair = (Pair)view.getTag(R.id.bound_observable);

        if(pair == null || pair.first != bindableString){
            if(pair!= null){
                view.removeTextChangedListener((TextWatcher) pair.second);
            }
            TextWatcherAdapter watcherAdapter = new TextWatcherAdapter(){
              public void onTextChanged(CharSequence s, int start,int bcforc,int count){
                  bindableString.setValue(s.toString());
              }
            };
            view.setTag(R.id.bound_observable, new Pair<>(bindableString, watcherAdapter));
            view.addTextChangedListener(watcherAdapter);
        }
        String newValue = bindableString.getValue();
        if(!view.getText().toString().equals(newValue)){
            view.setText(newValue);
        }
    }
}
