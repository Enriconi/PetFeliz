package petshop.integrador.com.br.petfeliz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmEmail;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener {

    @BindView(R.id.et_email)
    @NotEmpty (message = "E-mail não pode ficar em branco.") //entrada obrigatória no input
    @Email //validar email
    EditText EtEmail;

    @BindView(R.id.et_confirmar_email)
    @ConfirmEmail
    EditText EtConfirmarEmail;

    @BindView(R.id.et_senha)
    @NotEmpty (message = "Senha não pode ficar em branco.")
    @Password(min = 8, max = 50, scheme = Password.Scheme.ANY)
    EditText EtSenha;

    @BindView(R.id.et_confirmar_senha)
    @ConfirmPassword
    EditText EtConfirmarSenha;

    @OnClick(R.id.bt_submeter)
    public void onButtonClick(View view) {
        validator.validate();
    }

    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize ButterKnife
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "Validação deu certo!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
