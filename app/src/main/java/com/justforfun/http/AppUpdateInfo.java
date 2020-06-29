package com.justforfun.http;

public class AppUpdateInfo {
    public String name;
    public int version;
    public String changelog;
    public String versionShort;

    public boolean showAD() {
        if (changelog == null) {
            changelog = "";
        }
        return changelog.trim().endsWith("1");
    }

}
