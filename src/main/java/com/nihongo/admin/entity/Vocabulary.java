package com.nihongo.admin.entity;

import javax.persistence.*;

@Entity
@Table(name = "vocabulary")
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    @Column(length = 40, nullable = false, unique = true)
    private String word;

    @Column(length = 40, nullable = false)
    private String kanJ;

    @Column(name = "pronounce", length = 40, nullable = false)
    private String read;

    @Column(length = 40, nullable = false)
    private String mean;

    @Column(length = 255, nullable = false)
    private String audio;

    @Column(length = 255, nullable = false)
    private String example;

    @Column(length = 40, nullable = false)
    private String level;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vocabulary)) return false;
        return id != null && id.equals(((Vocabulary) obj).getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getKanJ() {
        return kanJ;
    }

    public void setKanJ(String kanJ) {
        this.kanJ = kanJ;
    }


    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }


}
