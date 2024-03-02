package com.server.api.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "files")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "file_name", nullable = false)
    private String fileName;
    @Column(name = "peer_name", nullable = false)
    private String peerName;
}
