package xmlProcess;


import example.websocket.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class DatasetProcessor implements XmlProcessorList{
//    public static void main(String args[]) throws DocumentException, InterruptedException, IOException, URISyntaxException {
//        List w = new ArrayList();
//        Map w1 = new HashMap();
//        Map w2 = new HashMap();
//        w1.put("dataA", 7);
//        w1.put("dataB", 6);
//        w1.put("dataC", 5);
//        w2.put("dataA", 5555);
//        w2.put("dataB", 3);
//        w2.put("dataC", 2);
//        w.add(w1);
//        w.add(w2);
//        DatasetProcessor dd = new DatasetProcessor();
//        dd.generateXmlFile(w,"E:\\simulation\\java\\dianwang\\src\\main\\java\\zhaozipiao\\xmlProcessor\\data.xml");
//
//        File data = new File("E:\\simulation\\java\\dianwang\\src\\main\\java\\zhaozipiao\\xmlProcessor\\data.xml");
//        dd.analyzeXmlFile(data);
//    }
    /**
     *
     * @param dataset: 数据集
     * @param filepath: 生成的xml文件存放的位置
     * @return xml文件
     */
    public boolean generateXmlFile(List<Map> dataset, String filepath) throws URISyntaxException, InterruptedException, IOException {
//        List<Map> dataset = (List<Map>) data;
        Document document = DocumentHelper.createDocument();
        //添加元素 dataSet
        Element datasetElement = document.addElement("dataset");
        //给dataSet元素添加属性 xmlns:xs="http://www.w3.org/2001/XMLSchema"
//        datasetElement.addAttribute("xmlns:xs", "http://www.w3.org/2001/XMLSchema");
//        datasetElement.addAttribute("xs:noNamespaceSchemaLocation", "model.xsd");
        for(int i = 0; i < dataset.size(); i++){
            Element dataElement = datasetElement.addElement("data");
            dataElement.addAttribute("id", String.valueOf(i));
            //添加元素
            Set s = dataset.get(i).entrySet();
            Iterator ite = s.iterator();
            while (ite.hasNext()){
                Map.Entry entry =  (Map.Entry) ite.next();
                Element element1 = dataElement.addElement(String.valueOf(entry.getKey()));
                element1.setText(String.valueOf(entry.getValue()));
            }
        }
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");    // 指定XML编码
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(new FileWriter(filepath),format);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(filepath);
        FileUtils test=new FileUtils();
        boolean d;
        d = test.transportFileToByte(file,ws);
        return d;
    }
    /**
     *
     * @param xmlFile : xml文件
     * @return List<Map>类型的数据
     */
    public List<Map> analyzeXmlFile(File xmlFile) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(xmlFile);
        //获取根元素modelParam
        Element dataRootElement = document.getRootElement();
        //获取根元素的子元素
        List<Element> dataElement = dataRootElement.elements();
        List dataset = new ArrayList();
        //获取modelParam元素下的子元素
        for (int i = 0; i < dataElement.size(); i++) {
            Map data = new HashMap();
            List<Element> oneModelParamEle = dataElement.get(i).elements();
            for (int j = 0; j < oneModelParamEle.size(); j++) {
                data.put(oneModelParamEle.get(j).getName(), oneModelParamEle.get(j).getText());
            }
            dataset.add(data);
        }
        System.out.println(dataset);
        return dataset;
    }
}
