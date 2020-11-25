package com.tivnan.studentls.bean;

/**
 * @project: studentls
 * @description:
 * @author: tivnan
 * @create: 2020-2020/11/25-下午12:39
 * @version:
 **/
public class Review {
    private String noteId;
    private Integer id;

    public Review(String noteId, Integer id) {
        this.noteId = noteId;
        this.id = id;
    }

    public Review() {
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
