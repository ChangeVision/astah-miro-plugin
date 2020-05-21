package com.change_vision.astah.lab.plugin.miro;

public class MiroAuth {
    private String token;
    private String boardId;
    private Boolean unsafeSSL;

    public void setToken(final String token) {
        this.token = token;
    }

    public void setBoardId(final String boardId) {
        this.boardId = boardId;
    }

    public String getToken() {
        return token;
    }

    public String getBoardId() {
        return boardId;
    }

    public Boolean getUnsafeSSL() {
        return unsafeSSL;
    }

    public void setUnsafeSSL(Boolean unsafeSSL) {
        this.unsafeSSL = unsafeSSL;
    }
}
