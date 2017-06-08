package com.baby.tech.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.baby.tech.activity.MainActivity;
import com.baby.tech.login.User;
import com.baby.tech.login.UserService;
import com.baby.tech.R;

public class RegisterActivity extends Activity {
	EditText username;
	EditText password;
	EditText age;
	RadioGroup sex;	
	Button register;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		findViews();
		register.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(username.getText().toString().equals("")|| password.getText().toString().equals("")||age.getText().toString().equals("")){
					Toast.makeText(RegisterActivity.this, "您有信息未填写哦！", Toast.LENGTH_SHORT).show();
					//break;
				}else{
				String name=username.getText().toString().trim();
				String pass=password.getText().toString().trim();
				String agestr=age.getText().toString().trim();
				String sexstr=((RadioButton)RegisterActivity.this.findViewById(sex.getCheckedRadioButtonId())).getText().toString();
				Log.i("TAG",name+"_"+pass+"_"+agestr+"_"+sexstr);
				UserService uService=new UserService(RegisterActivity.this);
				User user=new User();
				user.setUsername(name);
				user.setPassword(pass);
				user.setAge(Integer.parseInt(agestr));
				user.setSex(sexstr);
				uService.register(user);
				Toast.makeText(RegisterActivity.this, "注册成功，请输入登录信息！", Toast.LENGTH_LONG).show();
				
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), LoginActivity.class);
				//intent.putExtra(EXTRA_MESSAGE, message);
				startActivity(intent);
				finish();
				}
			}
		});
	}
	private void findViews() {
		username=(EditText) findViewById(R.id.usernameRegister);
		password=(EditText) findViewById(R.id.passwordRegister);
		age=(EditText) findViewById(R.id.ageRegister);
		sex=(RadioGroup) findViewById(R.id.sexRegister);
		register=(Button) findViewById(R.id.Register);
	}

}
