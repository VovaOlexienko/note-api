package com.github.notes.api.notes.service.entity;

import com.github.notes.api.common.config.constant.NoteStatus;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", length = 250, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private NoteStatus status;

    @Column(name = "notification_time")
    private LocalDateTime notificationTime;

    @Column(name = "order_id", nullable = false)
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "note_package_id", nullable = false)
    private NotePackage notePackage;
}