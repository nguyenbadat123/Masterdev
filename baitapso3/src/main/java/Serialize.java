import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import baitap.PeopleList;
import baitap.Person;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

public class Serialize {
    public static void main(String args[]) throws IOException{

        //Instantiating generated emp class
        Person p1 =new Person();
        Person p2 =new Person();
        PeopleList peopleList = new PeopleList();
        PeopleList peopleList1 = new PeopleList();
        List<Person> people = new ArrayList<>();
        List<Person> people2 = new ArrayList<>();

        p1.setFirstName("nguyen");
        p1.setLastName("dat");
        people.add(p1);
        peopleList.setTen(people);
        peopleList.setMaso(1);
        peopleList.setDiachi("thuy phuong");
        peopleList.setEmail("nguyen@gmail.com");
        peopleList.setPhuongtien("xe may");

        p2.setFirstName("abc");
        p2.setLastName("xyz");
        people2.add(p2);
        peopleList1.setTen(people2);
        peopleList1.setMaso(2);
        peopleList1.setPhuongtien("di bo");
        peopleList1.setEmail("abc@gmail.com");
        peopleList1.setDiachi("ha noi");

        DatumWriter<PeopleList> empDatumWriter = new SpecificDatumWriter<PeopleList>(PeopleList.class);
        DataFileWriter<PeopleList> empFileWriter = new DataFileWriter<PeopleList>(empDatumWriter);

        empFileWriter.create(peopleList.getSchema(), new File("C:\\Users\\DELL\\IdeaProjects\\avro\\src\\main\\java\\baitap/example.avro"));

        empFileWriter.append(peopleList);
        empFileWriter.append(peopleList1);


        empFileWriter.close();

        System.out.println("data successfully serialized");
    }
}
