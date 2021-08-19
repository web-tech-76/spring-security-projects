package provider02.api.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private Object inObject;

    private String message;

    private int status;

}
