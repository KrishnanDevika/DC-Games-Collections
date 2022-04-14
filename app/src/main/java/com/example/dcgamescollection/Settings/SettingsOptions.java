package com.example.dcgamescollection.Settings;

public class SettingsOptions {
    private int settingIcon;
    private String settingItems;
    private String settingsDesc;

    public SettingsOptions(int settingIcon, String settingItems, String settingsDesc) {
        this.settingIcon = settingIcon;
        this.settingItems = settingItems;
        this.settingsDesc = settingsDesc;
    }

    public int getSettingIcon() {
        return settingIcon;
    }

    public void setSettingIcon(int settingIcon) {
        this.settingIcon = settingIcon;
    }

    public String getSettingItems() {
        return settingItems;
    }

    public void setSettingItems(String settingItems) {
        this.settingItems = settingItems;
    }

    public String getSettingsDesc() {
        return settingsDesc;
    }

    public void setSettingsDesc(String settingsDesc) {
        this.settingsDesc = settingsDesc;
    }
}
