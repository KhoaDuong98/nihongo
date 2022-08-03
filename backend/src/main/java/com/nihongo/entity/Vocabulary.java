package com.nihongo.entity;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@Entity
@Table(name = "vocabulary")
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 10, nullable = false)
    private Long vocabulary_id;

    @Column(length = 10485760, nullable = false)
    private String word;

    @Column(length = 10485760, nullable = false)
    private String kanJ;

    @Column(name = "pronounce", length = 10485760, nullable = false)
    private String read;

    @Column(length = 10485760, nullable = false)
    private String mean;

    @Column(length = 10485760, nullable = false)
    private String audio;

    @Column(length = 10485760, nullable = false)
    private String example;

    @Column(length = 10485760, nullable = false)
    private String example1;

    @Column(length = 10485760, nullable = false)
    private String level;

    @Column(name = "category_vocabulary", length = 10485760, nullable = false)
    private String categoryVocabulary;

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

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategoryVocabulary() {
        return categoryVocabulary;
    }

    public void setCategoryVocabulary(String categoryVocabulary) {
        this.categoryVocabulary = categoryVocabulary;
    }

    public String getExample1() {
        return example1;
    }

    public void setExample1(String example1) {
        this.example1 = example1;
    }

    public Long getVocabulary_id() {
        return vocabulary_id;
    }

    public void setVocabulary_id(Long vocabulary_id) {
        this.vocabulary_id = vocabulary_id;
    }

    @Override
    public String toString() {
        return "Vocabulary{" +
                "id=" + id +
                ", vocabulary_id=" + vocabulary_id +
                ", word='" + word + '\'' +
                ", kanJ='" + kanJ + '\'' +
                ", read='" + read + '\'' +
                ", mean='" + mean + '\'' +
                ", audio='" + audio + '\'' +
                ", example='" + example + '\'' +
                ", example1='" + example1 + '\'' +
                ", level='" + level + '\'' +
                ", categoryVocabulary='" + categoryVocabulary + '\'' +
                '}';
    }
}
