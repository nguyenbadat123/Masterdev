import java.io.File;
import java.io.IOException;

import baitap.PeopleList;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;

public class Deserialize {
    public static void main(String args[]) throws IOException{

        //DeSerializing the objects
        DatumReader<PeopleList> empDatumReader = new SpecificDatumReader<PeopleList>(PeopleList.class);

        //Instantiating DataFileReader
        DataFileReader<PeopleList> dataFileReader = new DataFileReader<PeopleList>(new
                File("C:\\Users\\DELL\\IdeaProjects\\avro\\src\\main\\java\\baitap\\example.avro"), empDatumReader);
        PeopleList em=null;

        while(dataFileReader.hasNext()){

            em=dataFileReader.next(em);
            System.out.println(em);
        }
    }
}