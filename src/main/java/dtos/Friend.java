package dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
public class Friend {

    private String firstname;
    private String lastname;
    private String id;
    private String age;
    private List<Address> Address;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
}