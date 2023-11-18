package co.com.example.r2dbc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "user_documents")
@Entity
public class DocumentData {
    @Id
    private String number;
    private String typeDocument;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;
}
