package com.pixeltrice.springbootimportcsvfileapp.payload;

public class ResponseMessage {

    private String messaage;
    private String fileDownloadUri;

    public ResponseMessage(String messaage, String fileDownloadUri) {
        this.messaage = messaage;
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getMessaage() {
        return messaage;
    }

    public void setMessaage(String messaage) {
        this.messaage = messaage;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }
}
