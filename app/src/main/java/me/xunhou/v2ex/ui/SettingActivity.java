package me.xunhou.v2ex.ui;

import android.os.Bundle;

import com.kenumir.materialsettings.MaterialSettingsActivity;
import com.kenumir.materialsettings.items.DividerItem;
import com.kenumir.materialsettings.items.HeaderItem;
import com.kenumir.materialsettings.items.TextItem;
import com.kenumir.materialsettings.storage.PreferencesStorageInterface;
import com.kenumir.materialsettings.storage.StorageInterface;

import me.xunhou.v2ex.model.V2EXSettingHelper;

/**
 * Created by ihgoo on 2015/5/22.
 */
public class SettingActivity extends MaterialSettingsActivity implements TextItem.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        V2EXSettingHelper settingHelper = V2EXSettingHelper.getInstance();
        String username = settingHelper.getUsername();
        addItem(new HeaderItem(getFragment()).setTitle("帐号信息"));
        addItem(new TextItem(getFragment(), "username").setTitle("用户名").setSubtitle(username).setOnclick(this));
        addItem(new DividerItem(getFragment()));
//        addItem(new CheckboxItem(getFragment(), "key1").setTitle("Checkbox item 1").setSubtitle("Subtitle text 1").setOnCheckedChangeListener(new CheckboxItem.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChange(CheckboxItem cbi, boolean isChecked) {
//                Toast.makeText(SettingActivity.this, "CHECKED: " + isChecked, Toast.LENGTH_SHORT).show();
//            }
//        }));
    }

    @Override
    public StorageInterface initStorageInterface() {
        return new PreferencesStorageInterface(this);
    }

    @Override
    public void onClick(TextItem item) {
        String name = item.getName();
        if (name.equals("username")){
            LoginDialog loginDialog = new LoginDialog(SettingActivity.this);
            loginDialog.show();
        }
    }
}
