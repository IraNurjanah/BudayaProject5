package com.example.jadwalshalat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    ArrayList<HashMap<String, String>> contactList;
    Button btnParseJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactList = new ArrayList<>();
        btnParseJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginXMLParsing();
            }
        });
    }

    private void beginXMLParsing(){
        InputStream is = null;
        try {
            is = getAssets().open("data.xml");
        }
        catch (IOException e ){
            e.printStackTrace();
        }

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        }catch (ParserConfigurationException e){
            e.printStackTrace();
        }

        Document doc = null;
        try {
            doc = documentBuilder.parse(is);
        }catch (SAXException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        Element element= doc.getDocumentElement();
        element.normalize();

        NodeList nList = doc.getElementsByTagName("rootnode");

        for (int i = 0; i< nList.getLength(); i++){
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element element2 = (Element) node;
                String id = element2.getElementsByTagName("Id").item(0).getTextContent();
                String name = element2.getElementsByTagName("Name").item(0).getTextContent();
                String age = element2.getElementsByTagName("Age").item(0).getTextContent();
                String stb = element2.getElementsByTagName("Stb").item(0).getTextContent();
                addingValuesToHashMap (id, name, age, stb);

            }
        }
        ListView lv = findViewById(R.id.idLvJson);
        ListAdapter adapter = new SimpleAdapter(MainActivity.this, contactList,
                R.layout.list_item, new String[]{"Id", "Name", "Age", "Stb"},
                new int[]{R.id.idSNo, R.id.idName, R.id.idAge, R.id.idStb});
        lv.setAdapter(adapter);
    }

    private void addingValuesToHashMap(String id, String name, String age, String stb) {
        HashMap<String, String> contact = new HashMap<>();
        contact.put("Id", id);
        contact.put("Name", name);
        contact.put("Age", age);
        contact.put("Stb", stb);
        contactList.add(contact);
    }
}
