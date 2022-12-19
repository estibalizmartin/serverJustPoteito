package ingredients.model;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
public class IngredientsPostRequest {

    @NotNull(message= "No puede ser nulo")
    @NotEmpty(message= "No puede estar vacio")
    @NotBlank(message= "No puede estar en blanco")
    private String name;

    @NotNull(message= "No puede ser nulo")
    @NotEmpty(message= "No puede estar vacio")
    @NotBlank(message= "No puede estar en blanco")
    private String type;

    public IngredientsPostRequest() {}

    public IngredientsPostRequest(
            @NotNull(message = "No puede ser nulo") @NotEmpty(message = "No puede estar vacio") @NotBlank(message = "No puede estar en blanco") String name,
            @NotNull @NotEmpty @NotBlank String type) {
        super();
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "IngredientsPostRequest{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
