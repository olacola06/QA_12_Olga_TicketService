package manager;

import lombok.Data;
import models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MyDataProvider{

    @DataProvider
    public Iterator<Object[]> registrationValidData() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/registrationValidData.csv"));
        String line = reader.readLine();
        while(line!=null){
            String data[]=line.split(",");
            list.add(new Object[]{User.builder().name(data[0]).surname(data[1]).email(data[2])
                    .password(data[3]).confirmPassword(data[4]).phone(data[5]).build()});
            line = reader.readLine();
        }

       return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> registrationValidDataLom(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{User.builder().name("Bob").surname("Bobby").email("bo@gmail.com").password("Aaaa$$$")
                .confirmPassword("Aaaa$$$").phone("+1122").build()});
        list.add(new Object[]{User.builder().name("Bob1").surname("Bobby1").email("bo@gmail.com").password("Aaaa$$$")
                .confirmPassword("Aaaa$$$").phone("+1122").build()});
        list.add(new Object[]{User.builder().name("Bob2").surname("Bobby2").email("bo@gmail.com").password("Aaaa$$$")
                .confirmPassword("Aaaa$$$").phone("+1122").build()});
        list.add(new Object[]{User.builder().name("Bob3").surname("Bobby3").email("bo@gmail.com").password("Aaaa$$$")
                .confirmPassword("Aaaa$$$").phone("+1122").build()});

        return list.iterator();
    }
}
