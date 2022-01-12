package com.liverary.book.springboot.domain.reading;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
public class ReadingId implements Serializable {
    @Column(name = "reading_id")
    private String bookey;
    private String userKey;
}
