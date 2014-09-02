package com.mahfuz.xmlreaderwriter;

/**
 * Created by IslamMha on 9/2/2014.
 */
import android.content.Context;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import org.xmlpull.v1.XmlSerializer;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.File;
import android.os.Environment;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import android.util.Log;
public class XMLHandler {

    List<Box> boxes ;
    XmlPullParserFactory pullParserFactory ;
    Context context ;
    File file ;
    XMLHandler(Context context){
        this.context = context;
    }
    public boolean createXmlFile(String fileName,ArrayList<Box> boxes) throws Exception{
        try{
           file = new File(Environment.getExternalStorageDirectory() + File.separator + fileName);
           file.createNewFile();
            if(file.exists()) {
                FileOutputStream fout = new FileOutputStream(file);
                OutputStreamWriter out = new OutputStreamWriter(fout);

                String data = writeToXmlFile(boxes);
                if(data!=null){
                    out.write(data);
                }

                out.close();
            }
        } catch (Exception e){

        }
        return false;
    }
    public String writeToXmlFile(ArrayList<Box> boxes){
        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        try{
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8",true);
            serializer.startTag("","box");
            serializer.attribute("","id",String.valueOf(boxes.size()));
            for(Box box:boxes){
                serializer.startTag("","box");
                serializer.attribute("","length",String.valueOf(box.getBox_length()));

                serializer.startTag("", "length");
                serializer.text(String.valueOf(box.getBox_length()));
                serializer.endTag("", "length");

                serializer.startTag("", "width");
                serializer.text(String.valueOf(box.getBox_width()));
                serializer.endTag("", "width");

                serializer.startTag("", "name");
                serializer.text(box.getBox_name());
                serializer.endTag("", "name");

                serializer.startTag("", "color");
                serializer.text(box.getBox_color());
                serializer.endTag("", "color");

                serializer.endTag("","box");
            }

            serializer.endTag("", "box");
            serializer.endDocument();
            return writer.toString();

        }catch (Exception e){
            return null;
        }
    }
    public List<Box> readFromXmlFile(String name){
        try {
            pullParserFactory  = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            InputStream is = context.getApplicationContext().getAssets().open(name);
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(is,null);
            parseXml(parser);

        }catch (Exception e){
            e.printStackTrace();
        }
        return boxes ;
    }
    private void parseXml(XmlPullParser parser) throws Exception{
        int eventType = parser.getEventType();
        Box current_box = null;
        while (eventType!=XmlPullParser.END_DOCUMENT){
            String name = null;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT :
                    boxes = new ArrayList<Box>();
                    break;
                case XmlPullParser.START_TAG :
                    name = parser.getName();
                    if(name.equalsIgnoreCase("box"))
                        current_box = new Box();
                    else if (current_box!=null){
                        if (name.equalsIgnoreCase("length")){
                            int len = Integer.parseInt(parser.nextText().trim());
                            current_box.setBox_length(len);
                        }
                        else if(name.equalsIgnoreCase("width")){
                            int w = Integer.parseInt(parser.nextText().trim());
                            current_box.setBox_width(w);
                        }
                        else if(name.equalsIgnoreCase("color")){
                            current_box.setBox_color(parser.nextText());
                        }
                        else if(name.equalsIgnoreCase("name")){
                            current_box.setBox_name(parser.nextText());
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if(name.equalsIgnoreCase("box") && current_box!=null){
                        boxes.add(current_box);
                    }
                    break;
            }
            eventType = parser.next();
        }
    }
}
