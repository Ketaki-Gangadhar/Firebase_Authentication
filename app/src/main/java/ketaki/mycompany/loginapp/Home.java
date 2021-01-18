package ketaki.mycompany.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Home extends AppCompatActivity {
private TextView one,email,welcome;
private FirebaseAuth mAuth;
private Button logout,show;
    FirebaseDatabase database ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        one = findViewById(R.id.textView1);

        email = findViewById(R.id.emailGiven);
          welcome = findViewById(R.id.textView2);
        logout = findViewById(R.id.logOut);
        show = findViewById(R.id.show);
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

    show.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {


                String my_email = user.getEmail();

                email.setText(my_email);


                one.setVisibility(View.VISIBLE);
                welcome.setVisibility(View.VISIBLE);
                email.setVisibility(View.VISIBLE);

            }
        }
    });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    mAuth.signOut();
                }
                else
                {
                    Toast.makeText(Home.this, "Error in Signing out", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}