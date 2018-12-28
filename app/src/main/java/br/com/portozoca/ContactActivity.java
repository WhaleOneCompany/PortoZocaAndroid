package br.com.portozoca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import br.com.portozoca.core.ActivityConstants;
import br.com.portozoca.core.ColorConstants;
import br.com.portozoca.core.ui.SpannableStringBuilder;

public class ContactActivity extends AppCompatActivity implements
        ColorConstants, ActivityConstants {

    TextView tv_title;
    TextView tv_name;
    TextView tv_location;
    TextView tv_github;
    TextView tv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        tv_title = findViewById(R.id.tv_title);
        tv_name = findViewById(R.id.tv_name);
        tv_location = findViewById(R.id.tv_location);
        tv_github = findViewById(R.id.tv_github);
        tv_email = findViewById(R.id.tv_email);

        tv_title.setText(SpannableStringBuilder.create(getString(R.string.contact)).
                size(2f).color(getColor(R.color.colorPrimaryDark)).build());

        String str_Name = getString(R.string.name);
        tv_name.setText(SpannableStringBuilder.create(str_Name.
                concat(": João Victor Perin")).
                size(1.2f).bold_underscored(0, str_Name.length()).build());

        String str_Location = getString(R.string.location);
        tv_location.setText(SpannableStringBuilder.create(str_Location.
                concat(": Novo Hamburgo/RS - Brazil")).
                size(1.2f).bold_underscored(0, str_Location.length()).build());

        String str_Github = getString(R.string.github);
        String github_Url = "joaovperin";
        String github_Link = "http://github.com/joaovperin";
        int start = str_Github.length() + 2;
        int end = start + github_Url.length();
        tv_github.setText(SpannableStringBuilder.create(str_Github.
                concat(": ").concat(github_Url)).link(github_Link, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE).
                size(1.2f).bold_underscored(0, str_Github.length()).build());
        tv_github.setMovementMethod(LinkMovementMethod.getInstance()); // enable clicking on url span

        String str_Email = getString(R.string.email);
        tv_email.setText(SpannableStringBuilder.create(str_Email.
                concat(": joaovperin@live.com")).
                size(1.2f).bold_underscored(0, str_Email.length()).build());
    }

    public void onClickBackButton(View v){
        setResult(ID_CONTACT_ACTIVITY);
        finish();
    }

}
