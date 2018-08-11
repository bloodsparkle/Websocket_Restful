package xmlProcess;


import example.websocket.FileUtils;
import example.websocket.connectWebsocket;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.Map.Entry;

public class ModuleProcessor implements XmlProcessorMap {
//    public static void main(String args[]) throws DocumentException, InterruptedException, IOException, URISyntaxException {
////        List<Map<String, Integer>>  w = new ArrayList<Map<String, Integer>>();
//        Map w = new HashMap();
//        List l = new ArrayList();
//        l.add(2);
//        l.add(4);
//        w.put("a", 3);
//        w.put("b", 4);
//        w.put("c","dtg");
//        w.put("d",l);
//        ModuleProcessor dd = new ModuleProcessor();
//        dd.generateXmlFile(w,"E:\\simulation\\java\\dianwang\\src\\main\\java\\zhaozipiao\\xmlProcessor\\model.xml");
//
//        File model = new File("E:\\simulation\\java\\dianwang\\src\\main\\java\\zhaozipiao\\xmlProcessor\\model.xml");
//        dd.analyzeXmlFile(model);
//    }
    /**
     *
     * @param modelParam ：模型参数
     * @param filepath: 生成的xml文件存放的位置
     * @return xml文件
     */
    public boolean generateXmlFile(Map modelParam, String filepath) throws URISyntaxException, InterruptedException, IOException {
//        Map modelParam = (Map) param;
        Document document = DocumentHelper.createDocument();
        //添加元素 modelParam
        Element element = document.addElement("modelParam");
        //给modelParam元素添加属性 xmlns:xs="http://www.w3.org/2001/XMLSchema"
//        modelParamElement.addAttribute("xmlns:xs", "http://www.w3.org/2001/XMLSchema");
//        modelParamElement.addAttribute("xs:noNamespaceSchemaLocation", "model.xsd");
//        Element element = modelParamElement.addElement("ne");
        Set s = modelParam.entrySet();
        Iterator ite = s.iterator();
        while (ite.hasNext()){
            Entry entry =  (Entry) ite.next();
            Element element1 = element.addElement(String.valueOf(entry.getKey()));
            element1.setText(String.valueOf(entry.getValue()));
        }
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");    // 指定XML编码
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(new FileWriter(filepath), format);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(filepath);
        FileUtils test=new FileUtils();
        boolean d;
        connectWebsocket ws = new connectWebsocket(new URI("ws://localhost:5001/websocket"));
        d = test.transportFileToByte(file,ws);
        return d;
    }

    /**
     *
     * @param xmlFile : xml文件
     * @return Map类型的数据
     */
    public Map analyzeXmlFile(File xmlFile) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(xmlFile);
        //获取根元素modelParam
        Element modelParamElement = document.getRootElement();
        //获取根元素的子元素
        List<Element> modelElement = modelParamElement.elements();
        Map model = new HashMap();
        Map modelParam = new HashMap();
        //获取modelParam元素下的子元素
        for (int i = 0; i < modelElement.size(); i++) {
            List<Element> oneModelParamEle = modelElement.get(i).elements();
            for (int j = 0; j < oneModelParamEle.size(); j++) {
                modelParam.put(oneModelParamEle.get(j).getName(), oneModelParamEle.get(j).getText());
            }
            model.put(String.valueOf(modelElement.get(i)), modelParam);
        }
        System.out.println(model);
        return model;
    }
//    public Map analyzeXmlFile(File xmlFile) throws DocumentException {
//        //todo 实现List<Map>
//        SAXReader saxReader = new SAXReader();
//        Document document = saxReader.read(xmlFile);
//        //获取根元素modelParam
//        Element modelParamElement = document.getRootElement();
//        //获取modelParam元素下的子元素
//        Iterator<Element> oneElementItor = modelParamElement.elementIterator();
//        Map<String, String> modelParam = new HashMap<String, String>();
//        while (oneElementItor.hasNext()) {
//            Element e1 = oneElementItor.next();
//            modelParam.put(e1.getName(), e1.getText());
//        }
//        System.out.println(modelParam);
//        return modelParam;
//    }
}

