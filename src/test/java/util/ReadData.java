package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadData {

    String group = "";

    public ArrayList getData() throws IOException {

        //Completed read enum/excel & store all group name in ArrayList
        ArrayList<String> facebookGroupList = new ArrayList<String>();
        BufferedReader facebookGroupNameList = new BufferedReader(new FileReader("jars\\facebookGroupNameList.txt"));

        while (group != null) {
            group = facebookGroupNameList.readLine();
            if (group != null)
                facebookGroupList.add(group);
        }
        return facebookGroupList;
    }
}
