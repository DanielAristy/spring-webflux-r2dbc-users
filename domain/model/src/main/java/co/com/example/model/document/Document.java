package co.com.example.model.document;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Document {
    private String number;
    private String typeDocument;
}