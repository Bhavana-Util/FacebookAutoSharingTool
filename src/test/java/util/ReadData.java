package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ReadData {

    String group = "";
    String dataStoreFilePath= Paths.get("").toAbsolutePath().toString()+"//src//test//java//res//facebookGroupNameList.txt";

    public ArrayList getData() throws IOException {

        //Completed read enum/excel & store all group name in ArrayList
        ArrayList<String> facebookGroupList = new ArrayList<String>();
        BufferedReader facebookGroupNameList = new BufferedReader(new FileReader(dataStoreFilePath));

        while (group != null) {
            group = facebookGroupNameList.readLine();
            if (group != null)
                facebookGroupList.add(group);
        }
        return facebookGroupList;
    }
}
