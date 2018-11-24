package revolhope.splanes.com.bitwallet.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import revolhope.splanes.com.bitwallet.R;
import revolhope.splanes.com.bitwallet.helper.RandomGenerator;

public class DialogGenerateParams extends DialogFragment {


    private EditText editText_sizeOther;
    private DialogCallback callback;
    private int mode;
    private int size;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Set password params");
        builder.setPositiveButton("Generate", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (size != RandomGenerator.SIZE_8 && size != RandomGenerator.SIZE_16 &&
                    size != RandomGenerator.SIZE_24 && size != RandomGenerator.SIZE_32 &&
                    size != RandomGenerator.SIZE_64) {

                    String str = editText_sizeOther.getText().toString();

                    try { size = Integer.parseInt(str); }
                    catch (NumberFormatException e) { size = -1; }
                }
                if (callback != null) callback.getResult(mode, size);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { }
        });

        return builder.create();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_generate_params, container, false);

        RadioButton rb_simple = view.findViewById(R.id.radioButton_simple);
        RadioButton rb_complex = view.findViewById(R.id.radioButton_complex);
        RadioButton rb_size8 = view.findViewById(R.id.radioButton_size8);
        RadioButton rb_size16 = view.findViewById(R.id.radioButton_size16);
        RadioButton rb_size24 = view.findViewById(R.id.radioButton_size24);
        RadioButton rb_size32 = view.findViewById(R.id.radioButton_size32);
        RadioButton rb_size64 = view.findViewById(R.id.radioButton_size64);
        RadioButton rb_sizeOther = view.findViewById(R.id.radioButton_sizeOther);
        editText_sizeOther = view.findViewById(R.id.editText_otherSize);

        View.OnClickListener listenerType = new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View view) {
                boolean checked = ((RadioButton) view).isChecked();
                switch(view.getId()) {
                    case R.id.radioButton_simple:
                        if (checked) mode = RandomGenerator.MODE_SIMPLE;
                        else size = -1;
                        break;
                    case R.id.radioButton_complex:
                        if (checked) mode = RandomGenerator.MODE_COMPLEX;
                        else size = -1;
                        break;
                }
            }
        };

        View.OnClickListener listenerSize = new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View view) {
                boolean checked = ((RadioButton) view).isChecked();
                switch(view.getId()) {
                    case R.id.radioButton_size8:
                        if (checked) size = RandomGenerator.SIZE_8;
                        else size = -1;
                        break;
                    case R.id.radioButton_size16:
                        if (checked) size = RandomGenerator.SIZE_16;
                        else size = -1;
                        break;
                    case R.id.radioButton_size24:
                        if (checked) size = RandomGenerator.SIZE_24;
                        else size = -1;
                        break;
                    case R.id.radioButton_size32:
                        if (checked) size = RandomGenerator.SIZE_32;
                        else size = -1;
                        break;
                    case R.id.radioButton_size64:
                        if (checked) size = RandomGenerator.SIZE_64;
                        else size = -1;
                        break;
                    case R.id.radioButton_sizeOther:
                        if (checked) editText_sizeOther.setEnabled(true);
                        else size = -1;
                        break;
                }
            }
        };

        rb_simple.setOnClickListener(listenerType);
        rb_complex.setOnClickListener(listenerType);
        rb_size8.setOnClickListener(listenerSize);
        rb_size16.setOnClickListener(listenerSize);
        rb_size24.setOnClickListener(listenerSize);
        rb_size32.setOnClickListener(listenerSize);
        rb_size64.setOnClickListener(listenerSize);
        rb_sizeOther.setOnClickListener(listenerSize);

        return view;
    }

    public void setCallback(DialogCallback callback) {
        this.callback = callback;
    }

    public interface DialogCallback {
        void getResult(int mode, int size);
    }
}