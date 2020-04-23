package models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.logind.BR;

import java.util.Objects;

public class BindableString extends BaseObservable {
    private String value;

    @Bindable
    public String getValue(){
        return value != null ? value :"";
    }
    public void setValue(String value){
        if(!Objects.equals(this.value,value)){
            this.value= value;
            notifyPropertyChanged(BR.value);
        }
    }
}
