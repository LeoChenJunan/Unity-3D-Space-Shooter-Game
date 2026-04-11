package tw.example.noboom;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogInActivity extends AppCompatActivity {

    private Button btn_login;
    private Button btn_register;
    private MySQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private EditText et_username;
    private EditText et_password;
    private TextView tv_test_show_usertable; //測試用
    private TextView tv_error_username;
    private TextView tv_error_password;
    private TextView tv_error_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        tv_error_username = (TextView) findViewById(R.id.tv_error_username);
        tv_error_password = (TextView) findViewById(R.id.tv_error_password);
        tv_error_login = (TextView) findViewById(R.id.tv_error_login);

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);

        helper = new MySQLiteOpenHelper(LogInActivity.this, "NoBoom", null, 1);
        db = helper.getWritableDatabase();

        tv_test_show_usertable = (TextView) findViewById(R.id.tv_test_show_usertable); //測試用

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();

                int user_id = queryLoginId(username, password);
                if (user_id == -1) {
                    clearEditText();
                    clearErrorMessage();
                    tv_error_login.setText("帳號或密碼錯誤，請重新輸入");
                } else {
                    clearEditText();
                    clearErrorMessage();
                    Intent intent = new Intent(LogInActivity.this, OptionsActivity.class);
                    intent.putExtra("user_id", user_id);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();

                if (username.isEmpty()) {
                    clearEditText();
                    clearErrorMessage();
                    tv_error_username.setText("名稱不能是空");
                } else if (password.isEmpty()) {
                    clearEditText();
                    clearErrorMessage();
                    tv_error_password.setText("密碼不能是空");
                } else if (checkUserNameExist(username)) {
                    clearEditText();
                    clearErrorMessage();
                    tv_error_username.setText("此名稱已經存在");
                } else {
                    clearEditText();
                    clearErrorMessage();
                    insertUser(username, password);
                    Toast.makeText(LogInActivity.this, "註冊成功", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean checkUserNameExist(String username) {
        Cursor c = db.query("users", null, "user_name=?", new String[]{username}, null, null, null);
        boolean result = c.getCount() == 1;
        c.close();
        return result;
    }

    private void clearEditText() {
        et_username.setText("");
        et_password.setText("");
    }

    private void clearErrorMessage() {
        tv_error_username.setText("");
        tv_error_password.setText("");
        tv_error_login.setText("");
    }

    private int queryLoginId(String username, String password) { //-1代表登入錯誤
        Cursor c = db.query("users", new String[]{"user_id"}, "user_name=? and password=?", new String[]{username, password}, null, null, null);
        int result = -1;
        if (c.getCount() != 0) {
            c.moveToNext();
            result = c.getInt(0);
        }
        c.close();
        return result;
    }

    private void insertUser(String username, String password) {
        ContentValues cv = new ContentValues();
        cv.put("user_name", username);
        cv.put("password", password);
        db.insert("users", null, cv);
    }
}