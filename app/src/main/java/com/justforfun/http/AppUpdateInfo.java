package com.justforfun.http;

public class AppUpdateInfo {
    public String name;
    public int version;
    public int build;
    public String changelog;
    public String versionShort;

//    public int intVersion() {
//        return Integer.parseInt(version);
//    }

    public boolean showAD() {
        if (changelog == null) {
            changelog = "";
        }
        return changelog.trim().endsWith("1");
    }

}
