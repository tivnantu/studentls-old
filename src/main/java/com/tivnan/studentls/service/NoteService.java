package com.tivnan.studentls.service;

import com.tivnan.studentls.bean.Note;
import com.tivnan.studentls.bean.NoteExample;
import com.tivnan.studentls.bean.vo.NoteWithStuName;
import com.tivnan.studentls.dao.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @project: studentls
 * @description: service for note
 * @author: tivnan
 * @create: 2020-2020/11/23-下午4:08
 * @version: 1.0
 **/
@Service
public class NoteService {

    @Autowired
    NoteMapper noteMapper;

    public void saveNote(Note note) {

        Note note1 = noteMapper.selectByPrimaryKey(note.getNoteId());

        if (note1 != null) {
            noteMapper.updateByPrimaryKey(note);
        } else {
            noteMapper.insertSelective(note);
        }


    }

    public Note queryNoteByNoteId(String noteId) {

        Note note = noteMapper.selectByPrimaryKey(noteId);
        return note;
    }

    public List<Note> getSubmitNotes(Integer studentId, int i) {

        NoteExample example = new NoteExample();
        NoteExample.Criteria criteria = example.createCriteria();
        criteria.andStudentIdEqualTo(studentId);
        if (i != 2) {
            criteria.andStateEqualTo(i);
        } else {
            criteria.andStateGreaterThanOrEqualTo(2);
        }

        List<Note> notes = noteMapper.selectByExample(example);

        return notes;
    }

    public Note submitNote(Note note) {

        long NumOfAuditors = noteMapper.countNumOfAuditors(note.getNoteId());

        note.setState(NumOfAuditors + 1);

        saveNote(note);

        return note;
    }

    public List<NoteWithStuName> getNotesNeedReview(Integer teacherId) {
        List<NoteWithStuName> notesNeedReview = noteMapper.getNotesNeedReview(teacherId);
        return notesNeedReview;
    }

    public void reviewNote(String noteId, String opinion) {
        Note note = noteMapper.selectByPrimaryKey(noteId);

        if ("agree".equals(opinion)) {
            if (note.getState() >= 2) {
                note.setState(note.getState() - 1);
                noteMapper.updateByPrimaryKeySelective(note);
            }
        } else {
            note.setState(-1);
            noteMapper.updateByPrimaryKeySelective(note);
        }


    }
}