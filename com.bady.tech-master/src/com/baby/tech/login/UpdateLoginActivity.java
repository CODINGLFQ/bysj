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

public class UpdateLoginActivity extends Activity {
	EditText username;
	EditText password;
	EditText newpassword;
	EditText age;
	RadioGroup sex;	
	Button update;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update);
		findViews();
		update.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(username.getText().toString().equals("")|| password.getText().toString().equals("")||age.getText().toString().equals("")){
					Toast.makeText(UpdateLoginActivity.this, "您有信息未填写哦！", Toast.LENGTH_SHORT).show();
				}else{
				String name=username.getText().toString().trim();
				String pass=password.getText().toString().trim();
				String newpass= newpassword.getText().toString().trim(); //新密码框对象
				String agestr=age.getText().toString().trim();
				String sexstr=((RadioButton)UpdateLoginActivity.this.findViewById(sex.getCheckedRadioButtonId())).getText().toString();
				Log.i("TAG!!!!!!!!!",name+"_"+pass+"_"+agestr+"_"+sexstr);
				UserService uService=new UserService(UpdateLoginActivity.this);
				User user=new User();
				user.setUsername(name);
				user.setPassword(newpass);  //把新的密码改进去!!!!!
				user.setAge(Integer.parseInt(agestr));
				user.setSex(sexstr);
				//uService.register(user);
				uService.update(user);
				Toast.makeText(UpdateLoginActivity.this, "修改成功！", Toast.LENGTH_LONG).show();
				
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
		update=(Button) findViewById(R.id.Update);
		username=(EditText) findViewById(R.id.usernameRegister);
		password=(EditText) findViewById(R.id.passwordRegister);
		newpassword=(EditText) findViewById(R.id.newpasswordRegister);//新密码框对象实例化
		age=(EditText) findViewById(R.id.ageRegister);
		sex=(RadioGroup) findViewById(R.id.sexRegister);

	}

}

