package tw.example.noboom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameOverActivity extends AppCompatActivity {

    private Button btn_return_option;
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent gameIntent = getIntent();
        user_id = gameIntent.getIntExtra("user_id", -1);

        btn_return_option = (Button) findViewById(R.id.btn_return_option);

        btn_return_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverActivity.this, OptionsActivity.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
                finish();
            }
        });
    }
}