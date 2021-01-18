package ketaki.mycompany.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    private Button register_button;
    private EditText rEmail , rPassword, rName;
    private TextView rTextView;
    private FirebaseAuth mAuth;
    FirebaseDatabase database ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_button = findViewById(R.id.registerButton);
        rEmail= findViewById(R.id.registerEmailId);
        rPassword = findViewById(R.id.registerPassword);
        rTextView = findViewById(R.id.registerTextView);
        rName = findViewById(R.id.registerName);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        rTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginActivity();
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = rEmail.getText().toString().trim();
                String password = rPassword.getText().toString().trim();
                String name = rName.getText().toString().trim();
                if(name.isEmpty()==true)
                {
                    rName.setError("Name is required !");
                    return;
                }

                if(email.isEmpty()==true)
                {
                    rEmail.setError("Email is required !");
                    return;
                }

                if(password.isEmpty()==true)
                {
                    rPassword.setError("Password is required !");
                    return;
                }
                if(password.length()<6)
                {
                    rPassword.setError("Password must have 6 or more characters");
                    return;
                }

                 mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful())
                         {
                             Toast.makeText(Register.this, "You have registered successfully!", Toast.LENGTH_SHORT).show();
                             startHomeActivity();
                         }
                         else
                         {
                             String error = task.getException().toString();
                             Toast.makeText(Register.this, "Failed to register :" +error, Toast.LENGTH_SHORT).show();
                         }
                     }
                 });
            }
        });
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}