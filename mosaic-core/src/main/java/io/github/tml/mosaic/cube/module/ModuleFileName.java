package io.github.tml.mosaic.cube.module;

public enum ModuleFileName {

    CUBE("cube"),
    API("api"),
    CONFIG("config"),
    VIEW("view");

    private String packageName;

    ModuleFileName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }
}
