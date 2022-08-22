import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Process {

    private String processId;
    private Long startTime;
    private Long endTime;

    public Process(String processId, Long startTime) {
        this.processId = processId;
        this.startTime = startTime;
    }
}
