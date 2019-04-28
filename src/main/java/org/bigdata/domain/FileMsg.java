package org.bigdata.domain;

/**
 * @author majx
 * @date 2019/4/23 0023
 */
public class FileMsg {
    private String filename;

    public FileMsg() {
    }

    public FileMsg(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
