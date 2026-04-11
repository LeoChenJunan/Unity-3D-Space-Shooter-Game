package tw.example.noboom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DifficultyActivity extends AppCompatActivity {

    private Button btn_easy;
    private Button btn_normal;
    private Button btn_hard;

    private int user_id;
    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        Intent loginIntent = getIntent();
        user_id = loginIntent.getIntExtra("user_id", -1);

        btn_easy = (Button) findViewById(R.id.btn_easy);
        btn_normal = (Button) findViewById(R.id.btn_normal);
        btn_hard = (Button) findViewById(R.id.btn_hard);
        btn_back = (Button)findViewById(R.id.btn_back);

        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficultyActivity.this, GameActivity.class);
                intent.putExtra("from", "difficultyActivity");
                intent.putExtra("user_id", user_id);
                intent.putExtra("difficulty", "easy");
                startActivity(intent);
                finish();
            }
        });

        btn_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficultyActivity.this, GameActivity.class);
                intent.putExtra("from", "difficultyActivity");
                intent.putExtra("user_id", user_id);
                intent.putExtra("difficulty", "normal");
                startActivity(intent);
                finish();
            }
        });

        btn_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficultyActivity.this, GameActivity.class);
                intent.putExtra("from", "difficultyActivity");
                intent.putExtra("user_id", user_id);
                intent.putExtra("difficulty", "hard");
                startActivity(intent);
                finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficultyActivity.this, OptionsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}